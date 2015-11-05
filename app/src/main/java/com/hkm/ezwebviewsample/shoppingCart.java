package com.hkm.ezwebviewsample;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import com.hkm.ezwebview.Util.Fx9C;
import com.hkm.ezwebview.app.BasicWebViewNormal;
import com.hkm.ezwebview.webviewclients.PaymentClient;

/**
 * Created by hesk on 22/9/15.
 */
public class shoppingCart extends BasicWebViewNormal {

    public static final String
            FRAGMENTTITLE_RESID = "title_resid",
            CART_ID = "comment_box_identification";
    public static final int FB_COMMENT = 9019;
    public static final int SINA_COMMENT = 9016;
    private int fragment_comment_box_type;

    public static shoppingCart B(final Bundle b) {
        final shoppingCart t = new shoppingCart();
        t.setArguments(b);
        return t;
    }

    public static Bundle shoppingIntent(final @StringRes int title, final String id_comment) {
        final Bundle n = new Bundle();
        n.putInt(FRAGMENTTITLE_RESID, title);
        n.putString(CART_ID, id_comment);
        return n;
    }

    public void kill() {
        killWebViewDefault();
    }

    protected <T extends PaymentClient> void setup_payment_gateway(T pay, String full_url) {
        Fx9C.setup_payment_gateway(pay, framer, block, betterCircleBar, full_url, "HypebeastStoreApp/1.0", 1600);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setup_payment_gateway(hbpaymentgateway.with(getActivity(), block), "https://store.hypebeast.com/cart");
    }

    protected int LayoutID() {
        return com.hkm.ezwebview.R.layout.fix_height_view;
    }

    public void complete() {
        completeloading();
    }

    public static class hbpaymentgateway extends PaymentClient {
        public static final String domain_start = "https://store.hypebeast.com/";
        public static final String domain_start_insecure = "http://store.hypebeast.com/";

        public static hbpaymentgateway with(Activity context, WebView mview) {
            final hbpaymentgateway pay = new hbpaymentgateway(context, mview);
            return pay;
        }

        @Override
        protected void triggerNative(Uri trigger_url) {
            super.triggerNative(trigger_url);
        }

        public hbpaymentgateway(Activity context, WebView fmWebView) {
            super(context, fmWebView);
            allow = new String[]{
                    domain_start_insecure,
                    domain_start + "cart",
                    domain_start + "cart/add",
                    domain_start + "login?_target_path=",
                    domain_start + "checkout/addressing",
                    "https://www.paypal.com/cgi-bin/webscr?",
                    domain_start + "returns"
            };
            // nativefunctions = new String[]{
            //start native app
            //        domain_start,
            //         domain_start_insecure

            // };

        }


    }

}
