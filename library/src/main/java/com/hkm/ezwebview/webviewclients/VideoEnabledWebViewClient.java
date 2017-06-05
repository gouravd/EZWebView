package com.hkm.ezwebview.webviewclients;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by hayton on 1/2/2017.
 */

public class VideoEnabledWebViewClient extends WebViewClient {

    @Override
    // Force links to be opened inside WebView and not in Default Browser
    // Thanks http://stackoverflow.com/a/33681975/1815624
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest resourceRequest){
        view.loadUrl(resourceRequest.getUrl().toString());
        return true;
    }
}
