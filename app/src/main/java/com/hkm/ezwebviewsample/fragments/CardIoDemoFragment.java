package com.hkm.ezwebviewsample.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import com.hkm.ezwebview.Util.Fx9C;
import com.hkm.ezwebview.models.WebContent;
import com.hkm.ezwebviewsample.R;

import org.apache.commons.io.IOUtils;

import java.io.IOException;

import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;

/**
 * Created by jack.cheung on 18/4/2017.
 * for more information, reference: https://github.com/card-io/card.io-Android-SDK
 */

public class CardIoDemoFragment extends Fragment {
    private static final String TAG = CardIoDemoFragment.class.getSimpleName();

    public static final int REQUEST_CODE_SCAN_CARD = 1000;

    RelativeLayout webViewContainer;
    WebView webView;

    public static CardIoDemoFragment newInstance() {
        return new CardIoDemoFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.card_io_demo_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        webViewContainer = (RelativeLayout)view.findViewById(R.id.webview_container);
        webView = (WebView)view.findViewById(R.id.webview);
        initWebView();
    }

    protected void initWebView() {
        String webContent;

        try {
            webContent = IOUtils.toString(getResources().getAssets().open("cardio_sample.html"));
        } catch (IOException ex) {
            Log.e(TAG, "failed to init webview");
            return;
        }

        webView.addJavascriptInterface(this, "CardIoInterface");
        Fx9C.with(getActivity())
                .setJavaScriptEnabled(true)
                .setWebViewHolder(webViewContainer)
                .setWebView(webView)
                .loadWebContent(new WebContent("https://www.example.com", "", webContent));
    }

    @JavascriptInterface
    public void showCardIoView() {
        Log.d(TAG, "showCardIoView");
        Intent scanIntent = new Intent(getActivity(), CardIOActivity.class);
        scanIntent.putExtra(CardIOActivity.EXTRA_HIDE_CARDIO_LOGO, true);
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true);
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false);
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false);
        scanIntent.putExtra(CardIOActivity.EXTRA_SUPPRESS_MANUAL_ENTRY, true);
        startActivityForResult(scanIntent, REQUEST_CODE_SCAN_CARD);
    }

    public void updateCardNumber(String cardNumber, String expiryDate) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            webView.loadUrl(String.format("javascript:updateCardNumber(\"%s\", \"%s\")", cardNumber, expiryDate));
        } else {
            webView.evaluateJavascript(String.format("updateCardNumber(\"%s\", \"%s\")", cardNumber, expiryDate), new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String s) {
                }
            });
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE_SCAN_CARD: {
                if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
                    CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);

                    String expiryDate = "";
                    if (scanResult.isExpiryValid()) {
                        expiryDate = String.format("%d/%d", scanResult.expiryMonth, scanResult.expiryYear);
                    }
                    updateCardNumber(scanResult.cardNumber, expiryDate);
                }
                break;
            }
        }
    }
}
