package com.hkm.ezwebviewsample.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebViewClient;

import com.hkm.ezwebview.Util.Fx9C;
import com.hkm.ezwebview.app.BasicWebViewNormal;
import com.hkm.ezwebview.bridge.BridgeHandler;
import com.hkm.ezwebview.bridge.BridgeWebView;
import com.hkm.ezwebview.bridge.BridgeWebViewClient;
import com.hkm.ezwebview.bridge.CallBackFunction;
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

    BridgeWebView bridgeWebView;

    @Override
    protected int getLayoutId() {
        return R.layout.jsvi;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onViewCreated(View v, Bundle b) {
        initBinding(v);
        if (block instanceof BridgeWebView) {
            bridgeWebView = (BridgeWebView) block;
        }
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        CartPaymentWebiViewClient webViewClient = new CartPaymentWebiViewClient(bridgeWebView);

        try {
            Fx9C.with()
                    .setWebView(bridgeWebView)
                    .setWebViewClient(webViewClient)
                    .setWebViewHolder(framer)
                    .setProgressBar(betterCircleBar)
                    .setAnimationDuration(1600)
                    .setEnableZoomSupport()
                    .setShowZoomButton()
                    .loadUrl(
                            LOCATION_SEATPLAN,
                            "seat_plan_arrangement",
                            new BridgeHandler() {
                                @Override
                                public void handler(String data, CallBackFunction function) {
                                    Log.d("seatplan", data);
                                   // function.onCallBack("got the data");
                                }
                            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static final String FILE_LOCATION = "file:///android_asset/testui.html";
    private static final String LOCATION_SEATPLAN = "http://13.228.58.92/website/webview/seatPlan.php?showid=21000041";

    public static class CartPaymentWebiViewClient extends BridgeWebViewClient {
        public static final String CART_URL = "https://www.amazon.com/cart";
        public static final String CART_URL_REDIRECTED = "https://www.amazon.com/gp/cart/view.html";
        public static final String CART_URL_SIGNIN = "https://www.amazon.com/ap/signin";


        public CartPaymentWebiViewClient(BridgeWebView webView) {
            super(webView);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (bridgeWebView != null) {
            bridgeWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (bridgeWebView != null) {
            bridgeWebView.onResume();
        }
    }

}
