package com.hkm.ezwebviewsample;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hkm.ezwebview.Util.Fx9C;
import com.hkm.ezwebview.app.BasicWebViewNormal;
import com.hkm.ezwebview.webviewclients.PaymentClient;

/**
 * Created by hesk on 22/9/15.
 */
public class ShoppingCartWebViewFragment extends BasicWebViewNormal {

    public static final String
            KEY_FRAGMENT_TITLE_ID = "title_resid";
    protected static final String USER_AGENT = String.format("AppIdentifier/%s", BuildConfig.VERSION_NAME);
    protected static final String CART_URL = "https://www.amazon.com/cart";

    public static ShoppingCartWebViewFragment newInstance(Bundle arguments) {
        final ShoppingCartWebViewFragment webViewFragment = new ShoppingCartWebViewFragment();
        webViewFragment.setArguments(arguments);
        return webViewFragment;
    }

    public static ShoppingCartWebViewFragment newInstance(final @StringRes int titleId) {
        Bundle arguments = new Bundle();
        arguments.putInt(KEY_FRAGMENT_TITLE_ID, titleId);

        return newInstance(arguments);
    }

    protected <T extends PaymentClient> void loadCartContent() {
        Activity activity = getActivity();
        if (activity == null) {
            return;
        }
        WebViewClient webViewClient = CartPaymentWebiViewClient.with(activity, block);

        Fx9C
                .with(getActivity())
                .setProgressBar(betterCircleBar)
                .setUserAgent(USER_AGENT)
                .setAnimationDuration(1600)
                .setWebViewClient(webViewClient)
                .setWebViewHolder(framer)
                .setWebView(block)
                .loadUrl(CART_URL);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadCartContent();
    }

    protected int getLayoutId() {
        return com.hkm.ezwebview.R.layout.fix_height_view;
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

}
