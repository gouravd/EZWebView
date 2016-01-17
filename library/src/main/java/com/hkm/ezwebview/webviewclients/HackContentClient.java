package com.hkm.ezwebview.webviewclients;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Build;
import android.webkit.WebView;

import com.evgenii.jsevaluator.JsEvaluator;
import com.evgenii.jsevaluator.interfaces.JsCallback;
import com.hkm.ezwebview.webviewleakfix.PreventLeakClient;

/**
 * Created by zJJ on 1/16/2016.
 */
public final class HackContentClient extends PreventLeakClient {
    public static final String PROGRAM_EXPORT_HTML_CONTENT = "javascript:window.Fc9sxHtmlViewer.processHTML('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');";
    public static final String PROGRAM_JS = "Fc9sxHtmlViewer.processHTML(document.getElementsByTagName('html')[0].innerHTML);";

    public interface LoadListener {
        @SuppressWarnings("unused")
        void processHTML(final String html);
    }

    public LoadListener getCB() {
        return cblsten;
    }

    private LoadListener cblsten = new LoadListener() {
        @Override
        public void processHTML(String html) {

        }
    };

    private JsEvaluator jsEva;

    public HackContentClient(Activity activity, LoadListener cb) {
        super(activity);
        cblsten = cb;

    }

    public HackContentClient(Activity activity, JsEvaluator ccb, LoadListener cb) {
        super(activity);
        jsEva = ccb;
        cblsten = cb;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        return false;
    }

    @SuppressLint("JavascriptInterface")
    @Override
    public void onPageStarted(
            WebView view,
            String url,
            Bitmap favicon) {

    }

    public void onPageFinished(WebView view, String url) {
        //  String javascript = "<script>"+PROGRAM_JS+"</script>";
        //  String base64 = Base64.encodeToString(data, Base64.DEFAULT);
        //  mWebView.loadUrl("data:text/html;charset=utf-8;base64," + base64);
        if (jsEva != null) {
            jsEva.evaluate(PROGRAM_JS, new JsCallback() {
                @Override
                public void onResult(final String result) {
                    cblsten.processHTML(result);
                }
            });
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                view.evaluateJavascript(PROGRAM_JS, null);
            } else {
                view.loadUrl(PROGRAM_EXPORT_HTML_CONTENT);
            }
        }
    }


}
