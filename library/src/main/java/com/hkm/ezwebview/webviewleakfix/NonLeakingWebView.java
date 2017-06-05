package com.hkm.ezwebview.webviewleakfix;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * see http://stackoverflow.com/questions/3130654/memory-leak-in-webview and http://code.google.com/p/android/issues/detail?id=9375
 * Note that the bug does NOT appear to be fixed in android 2.2 as romain claims
 * Also, you must call {@link #destroy()} from your activity's onDestroy method.
 * Author Heskeyo Kam
 */

public class NonLeakingWebView<T extends PreventLeakClient> extends WebView {
    private static final String TAG = NonLeakingWebView.class.getSimpleName();
    private static Field sConfigCallback;
    private OnScrollChangedCallback mOnScrollChangedCallback;

    public void setWebViewClient(Class<T> client, AppCompatActivity context) {
        try {
            Class[] cArg = new Class[1]; //Our constructor has 3 arguments
            cArg[0] = AppCompatActivity.class; //First argument is of *object* type Long
            T clientInstance = (T) client.getDeclaredConstructor(cArg).newInstance((AppCompatActivity) context);
            super.setWebViewClient(clientInstance);
        } catch (InstantiationException e) {
            Log.e(TAG, "failed to set webView client due to InstantiationException", e);
        } catch (IllegalAccessException e) {
            Log.e(TAG, "failed to set webView client due to IllegalAccessException", e);
        } catch (NoSuchMethodException e) {
            Log.e(TAG, "failed to set webView client due to NoSuchMethodException; wrong api support level?", e);
        } catch (InvocationTargetException e) {
            Log.e(TAG, "failed to set webView client due to InvocationTargetException", e);
        }
    }

    public NonLeakingWebView(Context context, Class<T> client) {
        super(context.getApplicationContext());
        //  super.setWebViewClient(new HBClient(this, b));
    }

    public NonLeakingWebView(Context context, AttributeSet attrs) {
        super(context.getApplicationContext(), attrs);
        // super.setWebViewClient(new HBClient(this, b));
    }

    public NonLeakingWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context.getApplicationContext(), attrs, defStyle);
        //  super.setWebViewClient(new HBClient(this, b));
    }


    //for android 4.1+
    private void enable_x_domain_41_after() {
        try {
            Field webviewclassic_field = WebView.class.getDeclaredField("mProvider");
            webviewclassic_field.setAccessible(true);
            Object webviewclassic = webviewclassic_field.get(this);
            Field webviewcore_field = webviewclassic.getClass().getDeclaredField("mWebViewCore");
            webviewcore_field.setAccessible(true);
            Object mWebViewCore = webviewcore_field.get(webviewclassic);
            Field nativeclass_field = webviewclassic.getClass().getDeclaredField("mNativeClass");
            nativeclass_field.setAccessible(true);
            Object mNativeClass = nativeclass_field.get(webviewclassic);

            Method method = mWebViewCore.getClass().getDeclaredMethod("nativeRegisterURLSchemeAsLocal", new Class[]{int.class, String.class});
            method.setAccessible(true);
            method.invoke(mWebViewCore, mNativeClass, "http");
            method.invoke(mWebViewCore, mNativeClass, "https");
        } catch (Exception e) {
            Log.d("wokao", "enablecrossdomain error");
            e.printStackTrace();
        }
    }

    private void enable_x_domain_41_before() {
        try {
            Field field = WebView.class.getDeclaredField("mWebViewCore");
            field.setAccessible(true);
            Object webviewcore = field.get(this);
            Method method = webviewcore.getClass().getDeclaredMethod("nativeRegisterURLSchemeAsLocal", String.class);
            method.setAccessible(true);
            method.invoke(webviewcore, "http");
            method.invoke(webviewcore, "https");
        } catch (Exception e) {
            Log.d("wokao", "enablecrossdomain error");
            e.printStackTrace();
        }
    }

    /**
     * http://stackoverflow.com/questions/22479677/android-webview-access-control-allow-origin
     */
    public void enablecrossdomain_js() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            enable_x_domain_41_after();
        } else {
            enable_x_domain_41_before();
        }

    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            if (sConfigCallback != null)
                sConfigCallback.set(null, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void onScrollChanged(final int l, final int t, final int oldl, final int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnScrollChangedCallback != null) mOnScrollChangedCallback.onScroll(l, t);
    }

    public OnScrollChangedCallback getOnScrollChangedCallback() {
        return mOnScrollChangedCallback;
    }

    public void setOnScrollChangedCallback(final OnScrollChangedCallback onScrollChangedCallback) {
        mOnScrollChangedCallback = onScrollChangedCallback;
    }

    /**
     * Impliment in the activity/fragment/view that you want to listen to the webview
     */
    public static interface OnScrollChangedCallback {
        void onScroll(int l, int t);
    }

    /**
     * Pauses any extra processing associated with this WebView and its
     * associated DOM, plugins, JavaScript etc. For example, if this WebView is
     * taken offscreen, this could be called to reduce unnecessary CPU or
     * network traffic. When this WebView is again "active", call onResume().
     * Note that this differs from pauseTimers(), which affects all WebViews.
     */
    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * Resumes a WebView after a previous call to onPause().
     */
    @Override
    public void onResume() {
        super.onResume();
    }
}