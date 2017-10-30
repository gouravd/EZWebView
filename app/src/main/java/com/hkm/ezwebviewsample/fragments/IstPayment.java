package com.hkm.ezwebviewsample.fragments;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.hkm.ezwebview.Util.Fx9C;
import com.hkm.ezwebview.app.BasicWebViewNormal;
import com.hkm.ezwebview.webviewclients.IstPaymentGatewayClient;
import com.hkm.ezwebviewsample.BuildConfig;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Created by hesk on 19/9/2017.
 */
public class IstPayment extends BasicWebViewNormal {

    private HashMap<String, String> getMapping() {
        HashMap<String, String> cMap = new HashMap<>();
/*
09-19 23:25:16.359 D:     "version": "1.0",
09-19 23:25:16.359 D:     "action": "AUTH_ONLY",
09-19 23:25:16.359 D:     "url": "https://uat2.octo3.net/ecommerce-web/pay3p",
09-19 23:25:16.359 D:     "merch_id": "100141000000301",
09-19 23:25:16.359 D:     "merch_order_id": "EO517179264110",
09-19 23:25:16.359 D:     "merch_txn_id": "EO517179264110",
09-19 23:25:16.359 D:     "term_id": "TERM_0001",
09-19 23:25:16.359 D:     "access_id": "b5RhNyli",
09-19 23:25:16.359 D:     "pay_type": "MC",
09-19 23:25:16.359 D:     "currency": 344,
09-19 23:25:16.359 D:     "amount": 4500,
09-19 23:25:16.359 D:     "return_url": "http://13.228.58.92/api_ticketing/api/transaction/octo3/datafeed",
09-19 23:25:16.359 D:     "locale": "en_us",
09-19 23:25:16.359 D:     "bill_to_city": "Hong Kong",
09-19 23:25:16.359 D:     "bill_to_country_code": "HK",
09-19 23:25:16.359 D:     "bill_to_street": "1 HI ST.",
09-19 23:25:16.359 D:     "bill_to_email_address": "abc@abc.com",
09-19 23:25:16.359 D:     "bill_to_first_name": "GD",
09-19 23:25:16.359 D:     "bill_to_last_name": "ASIA",
09-19 23:25:16.359 D:     "pay_item": "online ticketing",
09-19 23:25:16.359 D:     "remark": "mobile",
09-19 23:25:16.359 D:     "card_num_prefix": "",
09-19 23:25:16.359 D:     "merch_data": "yIIbUFtqvOCeGFgt0owX-QUzLpyOShzJUvPOT4mGEnDc,Ry39SwbMhjvYv8VM6i-STjrsFqlnRDeivE8RokZf64jE,7hl8qtP09-cf6M3zI_"
*/
        cMap.put("version", "1.0");
        cMap.put("action", "AUTH_ONLY");
        cMap.put("url", "https://uat2.octo3.net/ecommerce-web/pay3p");
        String txn = "EO51" + generateRandomString3();
        cMap.put("merch_id", "100141000000301");
        cMap.put("merch_order_id", txn);
        cMap.put("merch_txn_id", txn);

        cMap.put("pay_type", "MC");
        cMap.put("currency", "344");
        cMap.put("amount", "45000");

        //   cMap.put("return_url", "https://13.228.58.92/website/webview/octo3.php");
        cMap.put("locale", "en_us");
        cMap.put("bill_to_city", "Hong Kong");
        cMap.put("bill_to_country_code", "HK");
        cMap.put("bill_to_street", "ocinie i ifoenf");

        cMap.put("bill_to_email_address", "abc@abc.com");
        cMap.put("bill_to_first_name", "GD");
        cMap.put("bill_to_last_name", "ASIA");

        cMap.put("pay_item", "online ticketing");
        cMap.put("remark", "online payment");
        cMap.put("card_num_prefix", "");

        cMap.put("return_url", "https://13.228.58.92/api_ticketing/api/transaction/octo3/datafeed");
        cMap.put("merch_data", "yIIbUFtqvOCeGFgt0owX-QUzLpyOShzJUvPOT4mGEnDc,Ry39SwbMhjvYv8VM6i-STjrsFqlnRDeivE8RokZf64jE,7hl8qtP09-cf6M3zI_");


        cMap.put("term_id", "TERM_0001");
        cMap.put("access_id", "b5RhNyli");


        // cMap.put("merch_order_id", "EO518345562108");
        // cMap.put("merch_txn_id", "EO518345562108");
        return cMap;
    }

    public static final String TAG = "IstPayment";
    protected static final String USER_AGENT = String.format("AppIdentifier/%s", BuildConfig.VERSION_NAME);

    public static IstPayment newInstance() {
        return new IstPayment();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void traditional() {
        WebSettings settings = block.getSettings();
        framer.setVisibility(View.VISIBLE);
        block.setWebChromeClient(new WebChromeClient());
        betterCircleBar.setVisibility(View.GONE);
        //  block.loadUrl(getMapping().get("url"), getMapping());
        // block.loadUrl("https://uat2.octo3.net/ecommerce-web/pay3p");
        block.setVisibility(View.VISIBLE);
        IstPaymentDoneRedirection webViewClient = new IstPaymentDoneRedirection();
        // block.setWebViewClient(new WebViewClient());
        block.setWebViewClient(webViewClient);

        String url = getMapping().get("url");
        Collection<Map.Entry<String, String>> postData = getMapping().entrySet();
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head></head>");
        sb.append("<body onload=\"form1.submit()\">");
        sb.append(String.format("<form id=\"form1\" action=\"%s\" method=\"%s\">", url, "POST"));
        for (Map.Entry<String, String> item : postData) {
            sb.append(String.format("<input name=\"%s\" type=\"text\" value=\"%s\" />", item.getKey(), item.getValue()));
        }
        sb.append("</form></body></html>");
        Log.d(TAG, sb.toString());
        block.loadData(sb.toString(), "text/html", "UTF-8");
    }

    private class IstPaymentDoneRedirection extends IstPaymentGatewayClient {
        @Override
        protected void onTrueUriIntercepting(Uri trigger_url, Set<String> params) {
            Log.d(TAG, "investigateUrl ::: " + trigger_url.toString());
            if (getConfirmationFromPayment(params)) {
                interceptionPass();
                Log.d(TAG, "trigger_url ass is now done!!!!!!!" + getTxnId(trigger_url));
            }

            if (trigger_url.toString().equalsIgnoreCase("https://uat2.octo3.net/ecommerce-web/receiveCreditCard")) {
            }
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            Log.d(TAG, "onReceivedSslError! " + error.toString() + " : " + error.getPrimaryError());
            handler.proceed();
        }

    }


    protected void loadCartContent() {
        IstPaymentDoneRedirection webViewClient = new IstPaymentDoneRedirection();
        try {
            Fx9C
                    .with()
                    .setJavaScriptEnabled(true)
                    .setProgressBar(betterCircleBar)
                    .setAllowHTTPSMixedContentAllow()
                    .setWebViewClient(webViewClient)
                    .setWebViewHolder(framer)
                    .setWebView(block)
                    .loadUrlByPostAlternative(getMapping().get("url"), getMapping());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Handler h = new Handler();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            loadCartContent();
        }
        //kkao();
        //traditional
        // loadCartContent();
    }

    private void kkao() {
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadCartContent();
            }
        }, 2000);
    }

    /**
     * !!!! very important bug fix!
     * remember! this webview must be using fix_height_view in order to make the redirection successful
     * @return
     */
    @Override
    protected int getLayoutId() {
        return com.hkm.ezwebview.R.layout.fix_height_view;
    }

    private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final int RANDOM_STRING_LENGTH = 10;

    public static String generateRandomString() {
        StringBuffer randStr = new StringBuffer();
        for (int i = 0; i < RANDOM_STRING_LENGTH; i++) {
            Random rnd = new Random();
            int number = rnd.nextInt() % CHAR_LIST.length();
            char ch = CHAR_LIST.charAt(number);
            randStr.append(ch);
        }
        return randStr.toString();
    }


    public static String generateRandomString3() {
        return getSaltString().substring(0, 6);
    }


    public static String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }


}
