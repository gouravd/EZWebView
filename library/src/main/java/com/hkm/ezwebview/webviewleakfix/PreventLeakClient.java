package com.hkm.ezwebview.webviewleakfix;

import android.app.Activity;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.lang.ref.WeakReference;

/**
 * Created by hesk on 21/5/15.
 */
public class PreventLeakClient<A extends Activity> extends WebViewClient {
    protected WeakReference<A> activityRef;
    protected A activity;

    public PreventLeakClient(A activity) {
        activityRef = new WeakReference<A>(activity);
        this.activity = activityRef.get();
    }

}
