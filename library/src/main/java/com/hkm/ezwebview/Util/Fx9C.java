package com.hkm.ezwebview.Util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;

import com.hkm.ezwebview.BuildConfig;
import com.hkm.ezwebview.R;
import com.hkm.ezwebview.bridge.BridgeHandler;
import com.hkm.ezwebview.bridge.BridgeWebView;
import com.hkm.ezwebview.bridge.BridgeWebViewClient;
import com.hkm.ezwebview.bridge.CallBackFunction;
import com.hkm.ezwebview.bridge.DefaultHandler;
import com.hkm.ezwebview.models.WebContent;
import com.hkm.ezwebview.webviewclients.ChromeLoader;
import com.hkm.ezwebview.webviewclients.HClient;
import com.hkm.ezwebview.webviewclients.PaymentClient;
import com.hkm.ezwebview.webviewclients.URLClient;
import com.hkm.ezwebview.webviewleakfix.NonLeakingWebView;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by hesk on 6/8/15.
 * This is the container library for display the efficient commands from the APIs
 */
public class Fx9C {
    private static final String TAG = Fx9C.class.getSimpleName();

    public static void startToReveal(final ViewGroup view) {
        startToReveal(view, 1800);
    }

    public static void startToReveal(final ViewGroup view, final Runnable callback) {
        startToReveal(view, 1800, callback);
    }

    public static void startToRevealFast(final ViewGroup view) {
        startToReveal(view, 800);
    }

    public static void startToRevealFast(final ViewGroup view, final Runnable callback) {
        startToReveal(view, 800, callback);
    }

    private static void startToReveal(final ViewGroup view, final long animateDuration, final Runnable callback) {
        final Handler h = new Handler();
        view.setVisibility(View.VISIBLE);
        view.setAlpha(0f);
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewCompat.animate(view).setDuration(animateDuration)
                        .alpha(1f).withEndAction(callback);
            }
        }, 80);
    }

    private static void startToReveal(final ViewGroup view, final long animateDuration) {
        if (view == null) {
            Log.e(TAG, "view is null; ignoring call to startToReveal");
            return;
        }

        final Handler h = new Handler();
        view.setVisibility(View.VISIBLE);
        view.setAlpha(0f);
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewCompat.animate(view).setDuration(animateDuration)
                        .alpha(1f);
            }
        }, 80);
    }

    @Deprecated
    public static <T> void setup_content_block_wb(
            final T context,
            final RelativeLayout frame_holder,
            final NonLeakingWebView block,
            final String codeing,
            final HClient.Callback callback_url_bypass
    ) throws Exception {
        setup_content_block_wb(
                context,
                frame_holder,
                block,
                codeing,
                1500,
                false,
                callback_url_bypass,
                null);
    }

    @Deprecated
    public static <T> void setup_content_block_wb(
            final T context,
            final RelativeLayout frame_holder,
            final NonLeakingWebView block,
            final String codeing
    ) throws Exception {
        setup_content_block_wb(
                context,
                frame_holder,
                block,
                codeing,
                1500,
                !In32.hasNoVideoElement(codeing),
                null,
                null);
    }

    @Deprecated
    public static <T> void setup_content_block_wb(
            final T context,
            final RelativeLayout frame_holder,
            final NonLeakingWebView block,
            final String codeing,
            final Runnable afterEverythingIsDone
    ) throws Exception {
        setup_content_block_wb(
                context,
                frame_holder,
                block,
                codeing,
                1500,
                !In32.hasNoVideoElement(codeing),
                null,
                afterEverythingIsDone);
    }

    @Deprecated
    public static <T> void setup_content_block_wb(
            final T context,
            final RelativeLayout frame_holder,
            final NonLeakingWebView block,
            final String codeing,
            final boolean hasVideo,
            final HClient.Callback c,
            final Runnable cb
    ) throws Exception {
        setup_content_block_wb(context, frame_holder, block, codeing, 1500, hasVideo, c, cb);
    }

    @Deprecated
    public static <T> void setup_web_video(
            final T context,
            final RelativeLayout frame_holder,
            final NonLeakingWebView block,
            final CircleProgressBar circlebar,
            final String codeing,
            final HClient.Callback url_check_callback
    ) throws Exception {
        setup_web_video(context, frame_holder, block, circlebar, codeing, 2000, url_check_callback, null);
    }

    @Deprecated
    public static <T> void setup_web_video(
            final T context,
            final RelativeLayout frame_holder,
            final NonLeakingWebView block,
            final CircleProgressBar circlebar,
            final String codeing,
            final HClient.Callback url_check_callback,
            final Runnable reveal_callback
    ) throws Exception {
        setup_web_video(context, frame_holder, block, circlebar, codeing, 2000, url_check_callback, reveal_callback);
    }

    public static <T> void setup_content_block_custom_css(
            final T context,
            final RelativeLayout frame_holder,
            final NonLeakingWebView block,
            final String css,
            final String codeing,
            final boolean hasVideo,
            final HClient.Callback c,
            final Runnable cb
    ) throws Exception {
        setup_content_block_custom_css(context, frame_holder, block, css, codeing, 1500, hasVideo, c, cb);
    }

    @SuppressLint("SetJavaScriptEnabled")
    public static <T> void setup_content_block_custom_css(
            final T context,
            final RelativeLayout frame_holder,
            final NonLeakingWebView block,
            final String css,
            final String codeing,
            final int reveal_time,
            final boolean withVideoElements,
            final HClient.Callback urlByPass,
            final Runnable callback_webview
    ) throws Exception {
        HClient I2 = HClient.with(context, block);
        if (urlByPass != null) I2.setController(urlByPass);
        block.setWebViewClient(I2);
        if (withVideoElements) {
            block.setWebChromeClient(new ChromeLoader());
            block.getSettings().setJavaScriptEnabled(true);
            block.getSettings().setPluginState(WebSettings.PluginState.ON);
            block.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
        }
        //block.setScrollContainer(false);
        //block.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            block.loadDataWithBaseURL("", css + codeing, "text/html", "UTF-8", null);
        } else {
            block.loadDataWithBaseURL("", codeing, "text/html", "UTF-8", null);
        }
        block.setVisibility(View.VISIBLE);
        if (callback_webview == null)
            startToReveal(frame_holder, reveal_time);
        else
            startToReveal(frame_holder, reveal_time, callback_webview);
    }

    @Deprecated
    @SuppressLint("SetJavaScriptEnabled")
    private static <T> void setup_content_block_wb(
            final T context,
            final RelativeLayout frame_holder,
            final NonLeakingWebView block,
            final String codeing,
            final int reveal_time,
            final boolean withVideoElements,
            final HClient.Callback urlByPass,
            final Runnable callback_webview
    ) throws Exception {
        final String content_code_final = In32.cssByContentPost(with(context)) + codeing;
        HClient I2 = HClient.with(context, block);
        if (urlByPass != null) I2.setController(urlByPass);
        block.setWebViewClient(I2);
        if (withVideoElements) {
            block.setWebChromeClient(new ChromeLoader());
            block.getSettings().setJavaScriptEnabled(true);
            block.getSettings().setPluginState(WebSettings.PluginState.ON);
            block.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
        }
        //block.setScrollContainer(false);
        //block.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            block.loadDataWithBaseURL("", content_code_final, "text/html", "UTF-8", null);
        } else {
            block.loadDataWithBaseURL("", codeing, "text/html", "UTF-8", null);
        }
        block.setVisibility(View.VISIBLE);

        if (callback_webview == null)
            startToReveal(frame_holder, reveal_time);
        else
            startToReveal(frame_holder, reveal_time, callback_webview);
    }

    @Deprecated
    @SuppressLint("SetJavaScriptEnabled")
    private static <T> void setup_web_video(
            final T context,
            final RelativeLayout frame_holder,
            final NonLeakingWebView mVideo,
            final CircleProgressBar circlebar,
            final String codeing,
            final int reveal_time,
            final HClient.Callback on_url_passing,
            final Runnable callback_webview) throws Exception {
        final StringBuilder embeded_code = new StringBuilder();
        embeded_code.append(In32.cssByVideo(with(context)));
        embeded_code.append(codeing);
        mVideo.setWebChromeClient(new ChromeLoader(circlebar));
        mVideo.getSettings().setPluginState(WebSettings.PluginState.ON);
        mVideo.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
        HClient I2 = HClient.with(context, mVideo);
        if (on_url_passing != null) I2.setController(on_url_passing);
        mVideo.setWebViewClient(I2);
        mVideo.getSettings().setJavaScriptEnabled(true);
        mVideo.loadDataWithBaseURL("", embeded_code.toString(), "text/html; charset=utf-8", "UTF-8", null);
        mVideo.setVisibility(View.VISIBLE);
        if (callback_webview == null)
            startToReveal(frame_holder, reveal_time);
        else
            startToReveal(frame_holder, reveal_time, callback_webview);
    }

    @Deprecated
    @SuppressLint("SetJavaScriptEnabled")
    public static <T> void setup_web_video(
            final T context,
            final RelativeLayout frame_holder,
            final NonLeakingWebView mVideo,
            final CircleProgressBar circlebar,
            final String codeing,
            final int height,
            final int reveal_time,
            final HClient.Callback on_url_passing,
            final Runnable callback_webview) throws Exception, UnsupportedEncodingException {


        if (In32.hasNoVideoElement(codeing)) {
            throw new Exception("there is no embeded code found from the code. please specify iframe in the code");
        }
        final StringBuilder embeded_code = new StringBuilder();
        final String css = In32.cssRawName(with(context), R.raw.video_config_v2).replace("___HEIGHT___", height + "");
        //  final String embeded_code = css + codeing;
        embeded_code.append(css);
        embeded_code.append(codeing);
        mVideo.setWebChromeClient(new ChromeLoader(circlebar));
        mVideo.getSettings().setPluginState(WebSettings.PluginState.ON);
        mVideo.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
        HClient I2 = HClient.with(context, mVideo);
        if (on_url_passing != null) I2.setController(on_url_passing);
        mVideo.setWebViewClient(I2);
        mVideo.getSettings().setJavaScriptEnabled(true);
        mVideo.loadDataWithBaseURL("", embeded_code.toString(), "text/html; charset=utf-8", "UTF-8", null);
        mVideo.setVisibility(View.VISIBLE);
        if (callback_webview == null)
            startToReveal(frame_holder, reveal_time);
        else
            startToReveal(frame_holder, reveal_time, callback_webview);
    }

    public static String UserAgentTag(WebSettings ws, String tag) {
        final StringBuilder sb = new StringBuilder();
        sb.append(ws.getUserAgentString());
        sb.append(" ");
        sb.append(tag);
        return sb.toString();
    }

    @Deprecated
    public static void setup_content_block_wb(
            final RelativeLayout frame_holder,
            final WebView block,
            final CircleProgressBar betterCircleBar,
            final String html_content,
            final int reveal_time
    ) {
        try {
            block.setWebChromeClient(new ChromeLoader(betterCircleBar));
            block.loadDataWithBaseURL("", html_content, "text/html", "UTF-8", null);
            block.setVisibility(View.VISIBLE);
            Fx9C.startToReveal(frame_holder, reveal_time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    @SuppressLint("SetJavaScriptEnabled")
    public static <T extends PaymentClient> void setup_payment_gateway(
            final T paymentGateWay,
            final RelativeLayout frame_holder,
            final WebView block,
            final CircleProgressBar betterCircleBar,
            final String web_shop_uri,
            final String user_agent_tag,
            final int reveal_time
    ) {
        try {
            CookieManager.getInstance().setAcceptCookie(true);
            CookieSyncManager.getInstance().sync();
            block.getSettings().setUserAgentString(UserAgentTag(block.getSettings(), user_agent_tag));
            block.setWebChromeClient(new ChromeLoader(betterCircleBar));
            block.setWebViewClient(paymentGateWay);
            //  block.getSettings().setPluginState(WebSettings.PluginState.ON);
            // block.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
            block.getSettings().setJavaScriptEnabled(true);
            block.getSettings().setAppCacheEnabled(true);
            block.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
            block.loadUrl(web_shop_uri);
            block.setVisibility(View.VISIBLE);
            Fx9C.startToReveal(frame_holder, reveal_time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    @SuppressLint("SetJavaScriptEnabled")
    public static void setup_embedded_js_template(
            final RelativeLayout frame_holder,
            final NonLeakingWebView block,
            final CircleProgressBar betterCircleBar,
            final String final_template_html,
            final String query,
            final int reveal_time,
            final Context context
    ) {
        try {
            final StringBuilder embeded_code = new StringBuilder();
            //embeded_code.append(In32.cssByVideo(with(context)));
            embeded_code.append(final_template_html);
            block.getSettings().setJavaScriptEnabled(true);
            // block.getSettings().setAppCacheEnabled(true);
            block.getSettings().setPluginState(WebSettings.PluginState.ON);
            block.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                block.getSettings().setAllowUniversalAccessFromFileURLs(true);
                block.getSettings().setAllowContentAccess(true);
                block.getSettings().setAllowFileAccessFromFileURLs(true);
            }
            block.getSettings().setBlockNetworkLoads(false);
            //block.enablecrossdomain_js();
            block.setWebChromeClient(new ChromeLoader(betterCircleBar));
            block.loadDataWithBaseURL("http://www.example.com/?" + query, final_template_html, "text/html", "UTF-8", "");
            Log.d("dataLogWV", final_template_html);
            block.setVisibility(View.VISIBLE);
            startToReveal(frame_holder, reveal_time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    @SuppressLint("SetJavaScriptEnabled")
    public static <T extends PaymentClient> void setup_payment_gateway(
            final T paymentGateWay,
            final RelativeLayout frame_holder,
            final NonLeakingWebView block,
            final CircleProgressBar betterCircleBar,
            final String web_shop_uri,
            final String user_agent_tag,
            final int reveal_time
    ) {
        try {
            CookieManager.getInstance().setAcceptCookie(true);
            CookieSyncManager.getInstance().sync();
            block.getSettings().setUserAgentString(UserAgentTag(block.getSettings(), user_agent_tag));
            block.setWebChromeClient(new ChromeLoader(betterCircleBar));
            block.setWebViewClient(paymentGateWay);
            //  block.getSettings().setPluginState(WebSettings.PluginState.ON);
            // block.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
            block.getSettings().setJavaScriptEnabled(true);
            block.getSettings().setAppCacheEnabled(true);
            block.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
            block.loadUrl(web_shop_uri);
            block.setVisibility(View.VISIBLE);
            Fx9C.startToReveal(frame_holder, reveal_time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static <T> void setup_url_hypebrid(
            final T context,
            final RelativeLayout frame_holder,
            final NonLeakingWebView block,
            final CircleProgressBar betterCircleBar,
            final String url_in_full,
            final int reveal_time,
            final List<String> allow,
            final List<String> start,
            final URLClient.cb callback
    ) {
        try {

            URLClient caseclient = URLClient.with(context, block);
            if (allow.size() > 0 || start.size() > 0)
                caseclient.defineBoundaries(allow, start);
            if (callback != null)
                caseclient.setCallBack(callback);
            block.setWebChromeClient(new ChromeLoader(betterCircleBar));
            block.setWebViewClient(caseclient);
            // block.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            // block.getSettings().setSupportMultipleWindows(true);
            // block.getSettings().setPluginState(WebSettings.PluginState.ON);
            // block.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
            block.getSettings().setJavaScriptEnabled(true);
            // block.getSettings().setAppCacheEnabled(true);
            // block.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
            block.loadUrl(url_in_full);
            block.setVisibility(View.VISIBLE);
            Fx9C.startToReveal(frame_holder, reveal_time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void killWebView(NonLeakingWebView mWebView) {
        if (mWebView == null) return;
        //http://stackoverflow.com/questions/3815090/webview-and-html5-video
        if (mWebView.getVisibility() == View.GONE) {
            mWebView.loadUrl("about:blank");
            mWebView.destroy();
        }
    }

    public static void clearVideo(RelativeLayout frame, NonLeakingWebView mv) {
        if (hideSlider(frame)) {
            mv.loadDataWithBaseURL("", "", "text/html", "UTF-8", null);
            mv.setVisibility(View.INVISIBLE);
        }
    }


    private static <T> Activity with(T context) throws Exception {
        if (context instanceof AppCompatActivity) {
            Activity g = (Activity) context;
            return g;
        }
        if (context instanceof Fragment) {
            Fragment g = (Fragment) context;
            return g.getActivity();
        }
        if (context instanceof android.support.v4.app.Fragment) {
            android.support.v4.app.Fragment g = (android.support.v4.app.Fragment) context;
            return g.getActivity();
        }
        throw new Exception("please enter an activity or fragment");
    }

    private static boolean hideSlider(final Object view) {
        boolean killable = false;
        if (view == null) return killable;
        try {
            if (view instanceof RelativeLayout) {
                RelativeLayout v = (RelativeLayout) view;
                killable = v.getVisibility() != View.GONE;
                v.setVisibility(View.GONE);
            }
        } catch (Exception e) {
        }
        return killable;
    }

    /**
     * intermediate implementation to encapsulate Fx9C and provide consistent api
     */
    private final String DEFAULT_MIME_TYPE = "text/html";
    private final String DEFAULT_ENCODING = "UTF-8";

    private Context context;
    private boolean isAppCacheEnabled = false;
    private boolean isChromeDebugEnabled = false;
    private boolean isJavaScriptEnabled = true;
    private boolean allowAutomaticMediaPlayback = false;
    private boolean allowHTTPSMixedContent = false;
    private boolean zoomSupport = false;
    private boolean zoomSupportControl = false;
    private long animateDuration;
    private String baseUrl = "";
    private CacheMode cacheMode = CacheMode.LOAD_DEFAULT;
    private WebViewClient webViewClient = null;

    private RelativeLayout webViewHolder;
    private WebView webView = null;
    private ChromeLoader.OnCloseWindowCallback onCloseWindowCallback;
    private Runnable onCompleteCallback = null;
    private CircleProgressBar progressBar = null;
    private String userAgent = null;

    public static Fx9C with() {
        return new Fx9C();
    }

    private Fx9C() {
        CookieManager cookieManager = CookieManager.getInstance();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            CookieSyncManager.getInstance().sync();
        }
        cookieManager.setAcceptCookie(true);
        cacheMode = CacheMode.LOAD_DEFAULT;
        animateDuration = 500;
    }

    /**
     * Allow automatic media playback without user gesture
     * required for SoundCloud to work properly
     *
     * @param allow is allow?
     * @return self main
     */
    public Fx9C setAllowAutomaticMediaPlayback(boolean allow) {
        allowAutomaticMediaPlayback = allow;
        return this;
    }


    public Fx9C setAllowHTTPSMixedContent(boolean allow) {
        allowHTTPSMixedContent = allow;
        return this;
    }

    public Fx9C setAnimationDuration(long animationDuration) {
        this.animateDuration = animationDuration;
        return this;
    }

    public Fx9C setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public Fx9C setCacheMode(CacheMode cacheMode) {
        this.cacheMode = cacheMode;
        return this;
    }

    public Fx9C setChromeDebugEnabled(boolean isChromeDebugEnabled) {
        this.isChromeDebugEnabled = isChromeDebugEnabled;
        return this;
    }

    public Fx9C setAppCacheEnabled(boolean isAppCacheEnabled) {
        this.isAppCacheEnabled = isAppCacheEnabled;
        return this;
    }

    public Fx9C setJavaScriptEnabled(boolean isJavaScriptEnabled) {
        this.isJavaScriptEnabled = isJavaScriptEnabled;
        return this;
    }

    public Fx9C setOnCloseWindowCallback(ChromeLoader.OnCloseWindowCallback onCloseWindowCallback) {
        this.onCloseWindowCallback = onCloseWindowCallback;
        return this;
    }

    public Fx9C setOnCompleteCallback(Runnable callback) {
        onCompleteCallback = callback;
        return this;
    }

    public Fx9C setProgressBar(CircleProgressBar progressBar) {
        this.progressBar = progressBar;
        return this;
    }

    public Fx9C setShowZoomButton() {
        zoomSupportControl = true;
        return this;
    }

    public Fx9C setEnableZoomSupport() {
        zoomSupport = true;
        return this;
    }

    public Fx9C setDefaultUserAgentWithSuffix(String suffix) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            this.userAgent = String.format("%s %s", WebSettings.getDefaultUserAgent(context), suffix);
        } else {
            String jellyBeanDefaultUserAgent = "Mozilla/5.0 (Linux; U; Android 4.1.2; en-us; SCH-I535 Build/JZO54K) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 Mobile Safari/534.30";
            this.userAgent = String.format("%s %s", jellyBeanDefaultUserAgent, suffix);
        }
        return this;
    }

    public Fx9C setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public Fx9C setWebViewHolder(RelativeLayout layout) {
        webViewHolder = layout;
        return this;
    }

    public Fx9C setWebView(BridgeWebView webView) {
        this.webView = webView;
        context = webView.getContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, false);
        }
        return this;
    }

    public Fx9C setWebView(WebView webView) {
        this.webView = webView;
        context = webView.getContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
        }
        return this;
    }

    public Fx9C setWebViewClient(BridgeWebViewClient client) {
        this.webViewClient = client;
        return this;
    }

    public Fx9C setWebViewClient(WebViewClient client) {
        this.webViewClient = client;
        return this;
    }

    /**
     * @param callback client callback
     * @return self main module
     * @throws Exception if context is not defined or not of fragment / AppCompatActivity
     */
    public Fx9C setDefaultWebViewClientWithCallback(HClient.Callback callback) throws Exception {
        HClient webViewClient = HClient.with(context, webView);

        if (callback != null) {
            webViewClient.setController(callback);
        }

        this.webViewClient = webViewClient;
        return this;
    }

    private void updateWebViewCacheMode() {
        switch (cacheMode) {
            case LOAD_DEFAULT:
                webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
                break;
            case LOAD_NO_CACHE:
                webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
                break;
        }
    }

    private void updateWebChromeClient() {
        ChromeLoader webChromeClient = null;
        if (progressBar == null) {
            webChromeClient = new ChromeLoader();
        } else {
            webChromeClient = new ChromeLoader(progressBar);
        }
        if (onCloseWindowCallback != null) {
            webChromeClient.setOnCloseWindowCallback(onCloseWindowCallback);
        }
        webView.setWebChromeClient(webChromeClient);
    }

    /**
     * using webview content hack approach to build content base url post redirect
     *
     * @param webView  object
     * @param url      url endpoint
     * @param postData map in data
     */
    private static void webview_ClientPost(WebView webView, String url, Collection<Map.Entry<String, String>> postData) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head></head>");
        sb.append("<body onload=\"from_prexmqgox.submit()\">");
        sb.append(String.format("<form id=\"from_prexmqgox\" action=\"%s\" method=\"%s\">", url, "POST"));
        for (Map.Entry<String, String> item : postData) {
            sb.append(String.format("<input name=\"%s\" type=\"hidden\" value=\"%s\" />", item.getKey(), item.getValue()));
        }
        sb.append("</form></body></html>");
        webView.loadData(sb.toString(), "text/html", "UTF-8");
    }

    /**
     * @param filename    the uri path
     * @param handlerName the tag
     * @param bridge      the BridgeHandler
     * @throws Exception error
     */
    public void loadUrl(String filename, String handlerName, BridgeHandler bridge) throws Exception {
        if (this.webView instanceof BridgeWebView) {
            pre_config();
            BridgeWebView tv = (BridgeWebView) this.webView;
            tv.setDefaultHandler(new DefaultHandler());
            tv.loadUrl(filename);
            tv.registerHandler(handlerName, bridge);
            tv.callHandler("functionInJs", "testing is now started...", new CallBackFunction() {
                @Override
                public void onCallBack(String data) {
                    Log.d(TAG, data);
                }
            });
            tv.send("hello");
            post_config();
        }
    }

    public void loadUrl(String url) throws Exception {
        pre_config();
        webView.loadUrl(url);
        post_config();
    }

    /**
     * with additional header support
     *
     * @param url                   endpoint
     * @param additionalHttpHeaders headers
     * @throws Exception otherwise
     */
    public void loadUrl(String url, Map<String, String> additionalHttpHeaders) throws Exception {
        pre_config();
        webView.loadUrl(url, additionalHttpHeaders);
        post_config();
    }

    /**
     * post URL data with post
     *
     * @param url       uri
     * @param mapParams params
     * @throws Exception otherwise
     */
    public void loadUrlByPost(String url, Map<String, String> mapParams) throws Exception {
        pre_config();
        webView.postUrl(url, UriFactory.urlEncodeUTF8(mapParams).getBytes());
        post_config();
    }

    /**
     * post URL data with post
     *
     * @param url       uri
     * @param mapParams params
     * @throws Exception otherwise
     */
    public void loadUrlByPostAlternative(String url, Map<String, String> mapParams) throws Exception {
        pre_config();
        Collection<Map.Entry<String, String>> postData = mapParams.entrySet();
        webview_ClientPost(webView, url, postData);
        post_config();
    }


    /**
     * alternative use of web content config to get url
     *
     * @param webContent the object
     * @throws Exception otherwise
     */
    public void loadWebContent(WebContent webContent) throws Exception {
        pre_config();
        webView.loadDataWithBaseURL(webContent.getBaseUrl(), webContent.getRenderedHtml(), DEFAULT_MIME_TYPE, DEFAULT_ENCODING, webContent.getHistoryUrl());
        post_config();
    }


    /**
     * calling any handler name in run time
     *
     * @param HandlerName the name of the handler
     * @param data        the data in string
     * @param cb          the callback function
     */
    public void RTSendToJs(String HandlerName, String data, CallBackFunction cb) {
        if (webView instanceof BridgeWebView) {
            ((BridgeWebView) webView).callHandler(HandlerName, data, cb);
        }
    }

    /**
     * send to js
     *
     * @param data the data in string
     * @param cb   the callback function
     */
    public void RTSendDefaultToJs(String data, CallBackFunction cb) {
        if (webView instanceof BridgeWebView) {
            ((BridgeWebView) webView).send(data, cb);
        }
    }

    /**
     * send to js
     *
     * @param data the data in string
     */
    public void RTSendDefaultToJs(String data) {
        if (webView instanceof BridgeWebView) {
            ((BridgeWebView) webView).send(data);
        }
    }


    /**
     * the preconfiguration of the website settings
     *
     * @throws Exception otherwise
     */
    @SuppressLint("SetJavaScriptEnabled")
    private void pre_config() throws Exception {
        if (webView == null) {
            throw new Exception("webview is not initialized before loading web content");
        }
        if (webViewClient == null) {
            throw new Exception("webViewClient is not initialized before loading web content");
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.setWebContentsDebuggingEnabled(BuildConfig.DEBUG || isChromeDebugEnabled);
        }

        if (webViewClient instanceof BridgeWebViewClient && webView instanceof BridgeWebView) {
            BridgeWebViewClient bwc = (BridgeWebViewClient) webViewClient;
            BridgeWebView bwv = (BridgeWebView) webView;
            bwv.setWebViewClient(bwc);
        } else {
            webView.setWebViewClient(webViewClient);
        }


        WebSettings settings = this.webView.getSettings();
        if (userAgent != null) {
            settings.setUserAgentString(userAgent);
        }

        if (allowHTTPSMixedContent) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            }
        }
        if (webViewClient instanceof BridgeWebViewClient && webView instanceof BridgeWebView) {
            settings.setJavaScriptEnabled(true);
        } else
            settings.setJavaScriptEnabled(isJavaScriptEnabled);

        settings.setSupportZoom(zoomSupport);
        settings.setDisplayZoomControls(zoomSupportControl);
        settings.setBuiltInZoomControls(zoomSupportControl);
        settings.setMediaPlaybackRequiresUserGesture(!allowAutomaticMediaPlayback);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
            settings.setMediaPlaybackRequiresUserGesture(!allowAutomaticMediaPlayback);
        }

        updateWebChromeClient();
        updateWebViewCacheMode();
    }

    private void post_config() {
        webView.setVisibility(View.VISIBLE);
        if (onCompleteCallback == null) {
            Fx9C.startToReveal(webViewHolder, animateDuration);
        } else {
            Fx9C.startToReveal(webViewHolder, animateDuration, onCompleteCallback);
        }
    }

}
