package com.hkm.ezwebviewsample.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.hkm.ezwebview.Util.Fx9C;
import com.hkm.ezwebview.app.BasicWebViewNormal;
import com.hkm.ezwebview.bridge.CallBackFunction;
import com.hkm.ezwebview.webviewclients.PaymentClient;
import com.hkm.ezwebview.webviewleakfix.NonLeakingWebView;
import com.hkm.ezwebviewsample.R;


/**
 * Created by hesk on 28/8/2017.
 */

public class JsBridgetFragment extends BasicWebViewNormal {


    public static JsBridgetFragment newInstance() {
        return new JsBridgetFragment();
    }

    public JsBridgetFragment() {
    }

    NonLeakingWebView bblock;

    @Override
    protected void initBinding(View v) {
        super.initBinding(v);
        bblock = (NonLeakingWebView) v.findViewById(com.hkm.ezwebview.R.id.wv_content_block);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.jsvi;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onViewCreated(View v, Bundle b) {
        initBinding(v);

        Activity activity = getActivity();
        if (activity == null) {
            return;
        }


        WebViewClient webViewClient = CartPaymentWebiViewClient.with(activity, bblock);
        try {
            Fx9C
                    .with(getActivity())
                    .setProgressBar(betterCircleBar)
                    .setAnimationDuration(1600)
                    .setEnableZoomSupport()
                    .setShowZoomButton()
                    .setWebViewClient(webViewClient)
                    .setWebViewHolder(framer)
                    .setWebView(bblock)
                    .loadlUrlWithJsBridget("http://13.228.58.92/website/webview/seatPlan.php?showid=21000034", new CallBackFunction() {
                        @Override
                        public void onCallBack(String data) {
                            Log.d("seatplan", data);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static class CartPaymentWebiViewClient extends PaymentClient {
        public static final String CART_URL = "https://www.amazon.com/cart";
        public static final String CART_URL_REDIRECTED = "https://www.amazon.com/gp/cart/view.html";
        public static final String CART_URL_SIGNIN = "https://www.amazon.com/ap/signin";

        public static CartPaymentWebiViewClient with(Activity context, WebView mview) {
            return new CartPaymentWebiViewClient(context, mview);
        }

        @Override
        protected void triggerNative(Uri trigger_url) {
            super.triggerNative(trigger_url);
        }

        public CartPaymentWebiViewClient(Activity context, WebView fmWebView) {
            super(context, fmWebView);
            allow = new String[]{
                    CART_URL,
                    CART_URL_REDIRECTED,
                    CART_URL_SIGNIN
            };
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (block != null) {
            block.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (block != null) {
            block.onResume();
        }
    }

}
