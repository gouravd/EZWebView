package com.hkm.ezwebview.webviewclients;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.Set;

import static android.content.ContentValues.TAG;

/**
 * Created by hesk on 20/9/2017.
 */

public abstract class IstPaymentGatewayClient extends WebViewClient {
    protected boolean pass_once = false;

    /**
     * fixed the error from error of UNTRUSTED certificate redirection
     *
     * @param view    WebView
     * @param request WebResourceRequest
     * @return boolean
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        investigateUrl(request.getUrl().toString());
        return super.shouldInterceptRequest(view, request);
    }

    /**
     * fixed the error from error of UNTRUSTED certificate redirection
     *
     * @param view WebView
     * @param url  String
     * @return boolean
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return false;
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
        Log.d(TAG, "onReceivedHttpError " + errorResponse.getReasonPhrase() + " - " + errorResponse.getStatusCode() + " -- " + request.getUrl() + " M:" + request.getMethod() + " Main?" + (request.isForMainFrame() ? " MAINFRAME" : " NON-MAINFRAME"));
    }

    protected boolean isInterceptionPassYet() {
        return pass_once;
    }

    protected void interceptionPass() {
        pass_once = true;
    }

    abstract protected void onTrueUriIntercepting(Uri trigger_url, Set<String> params);

    private void investigateUrl(Uri trigger_url) {
        try {
            //Uri trigger_url = Uri.parse(url);
            if (trigger_url != null) {
                Set<String> e = trigger_url.getQueryParameterNames();
                onTrueUriIntercepting(trigger_url, e);
            }
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void investigateUrl(String uri) {
        try {
            Uri trigger_url = Uri.parse(uri);
            investigateUrl(trigger_url);
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected boolean getConfirmationFromPayment(Set<String> params) {
        return params.contains("txn_id") && params.contains("response_code") && !isInterceptionPassYet();
    }

    protected final String getTxnId(Uri params) {
        return params.getQueryParameter("txn_id");
    }

    protected final String getResponseCode(Uri params) {
        return params.getQueryParameter("response_code");
    }
}
