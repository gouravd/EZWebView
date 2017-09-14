package com.hkm.ezwebview.webviewclients;

import android.app.Activity;
import android.net.Uri;
import android.webkit.WebView;

/**
 * Created by hesk on 22/9/15.
 * for webviewclient
 */
public class PaymentClient extends HBCart {

    protected String[] allow, nativefunctions;

    public static PaymentClient with(Activity context, WebView mview) {
        final PaymentClient pay = new PaymentClient(context, mview);
        return pay;
    }

    public PaymentClient navigationAllowPreFix(String[] keys) {
        this.allow = keys;
        return this;
    }

    public PaymentClient nativeUriPrefix(String[] ban) {
        this.nativefunctions = ban;
        return this;
    }

    public PaymentClient(Activity context, WebView fmWebView) {
        super(context, fmWebView);
    }

    @Override
    protected void triggerNative(Uri trigger_url) {

    }

    @Override
    protected boolean interceptUrl(WebView view, String url) {
        if (allow != null) {
            for (int i = 0; i < allow.length; i++) {
                if (url.startsWith(allow[i]) || url.equalsIgnoreCase(allow[i])) {
                    return false;
                }
            }
        }
        if (nativefunctions != null) {
            for (int t = 0; t < nativefunctions.length; t++) {
                if (url.startsWith(nativefunctions[t]) || url.equalsIgnoreCase(nativefunctions[t])) {
                    triggerNative(Uri.parse(url));
                    return true;
                }
            }
        }
        return true;
    }


}
