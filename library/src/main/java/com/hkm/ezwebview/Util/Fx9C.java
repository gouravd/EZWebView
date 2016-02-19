package com.hkm.ezwebview.Util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;

import com.hkm.ezwebview.R;
import com.hkm.ezwebview.webviewclients.ChromeLoader;
import com.hkm.ezwebview.webviewclients.FBClient;
import com.hkm.ezwebview.webviewclients.HClient;
import com.hkm.ezwebview.webviewclients.PaymentClient;
import com.hkm.ezwebview.webviewclients.URLClient;
import com.hkm.ezwebview.webviewleakfix.NonLeakingWebView;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by hesk on 6/8/15.
 * This is the container library for display the efficient commands from the APIs
 */
public class Fx9C {
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

    public static void startToReveal(final ViewGroup view, final int timeinit, final Runnable callback) {
        final Handler h = new Handler();
        view.setVisibility(View.VISIBLE);
        view.setAlpha(0f);
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewCompat.animate(view).setDuration((long) timeinit)
                        .alpha(1f).withEndAction(callback);
            }
        }, 80);
    }

    public static void startToReveal(final ViewGroup view, final int timeinit) {
        final Handler h = new Handler();
        view.setVisibility(View.VISIBLE);
        view.setAlpha(0f);
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                ViewCompat.animate(view).setDuration((long) timeinit)
                        .alpha(1f);
            }
        }, 80);
    }

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
            block.loadDataWithBaseURL("", css + codeing, "text/html; charset=utf-8", "UTF-8", null);
        } else {
            block.loadDataWithBaseURL("", codeing, "text/html; charset=utf-8", "UTF-8", null);
        }
        block.setVisibility(View.VISIBLE);
        if (callback_webview == null)
            startToReveal(frame_holder, reveal_time);
        else
            startToReveal(frame_holder, reveal_time, callback_webview);
    }

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
            block.loadDataWithBaseURL("", content_code_final, "text/html; charset=utf-8", "UTF-8", null);
        } else {
            block.loadDataWithBaseURL("", codeing, "text/html; charset=utf-8", "UTF-8", null);
        }
        block.setVisibility(View.VISIBLE);

        if (callback_webview == null)
            startToReveal(frame_holder, reveal_time);
        else
            startToReveal(frame_holder, reveal_time, callback_webview);
    }

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


    /**
     * display facebook comment box
     *
     * @param context         context
     * @param frame_holder    frame holder
     * @param block           web view
     * @param betterCircleBar circle loading bar
     * @param url_id          the url id code
     * @param reveal_time     the time to reveal
     * @param <T>             the generic type
     */
    @SuppressLint("SetJavaScriptEnabled")
    public static <T> void setup_commentbox(
            final T context,
            final RelativeLayout frame_holder,
            final NonLeakingWebView block,
            final CircleProgressBar betterCircleBar,
            final String url_id,
            final int reveal_time
    ) {
        try {
            block.setWebChromeClient(new ChromeLoader(betterCircleBar));
            block.setWebViewClient(new FBClient(with(context), block));
            //  block.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
            // block.getSettings().setSupportMultipleWindows(true);
            block.getSettings().setPluginState(WebSettings.PluginState.ON);
            block.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
            block.getSettings().setJavaScriptEnabled(true);
            block.getSettings().setAppCacheEnabled(true);
            block.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
            if (url_id.equalsIgnoreCase("")) {
                block.loadUrl(CommentBoxUrl.sampleFacebookCommentBox());
            } else
                block.loadUrl(CommentBoxUrl.popbeeCommentBox(url_id));
            // Log.d("webview", ur);
            block.setVisibility(View.VISIBLE);
            Fx9C.startToReveal(frame_holder, reveal_time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String UserAgentTag(WebSettings ws, String tag) {
        final StringBuilder sb = new StringBuilder();
        sb.append(ws.getUserAgentString());
        sb.append(" ");
        sb.append(tag);
        return sb.toString();
    }

    public static void setup_content_block_wb(
            final RelativeLayout frame_holder,
            final WebView block,
            final CircleProgressBar betterCircleBar,
            final String html_content,
            final int reveal_time
    ) {
        try {
            block.setWebChromeClient(new ChromeLoader(betterCircleBar));
            block.loadDataWithBaseURL("", html_content, "text/html; charset=utf-8", "UTF-8", null);
            block.setVisibility(View.VISIBLE);
            Fx9C.startToReveal(frame_holder, reveal_time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
            block.loadDataWithBaseURL("http://hypetrak.com/?" + query, final_template_html, "text/html; charset=utf-8", "UTF-8", "");
            Log.d("dataLogWV", final_template_html);
            block.setVisibility(View.VISIBLE);
            startToReveal(frame_holder, reveal_time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void setup_template_body_overhead(
            final Activity context,
            final RelativeLayout frame_holder,
            final NonLeakingWebView block,
            final String template_body,
            final String codeing,
            final int reveal_time,
            final boolean withVideoElements,
            final HClient.Callback urlByPass,
            final Runnable callback_webview
    ) throws Exception {
        HClient I2 = HClient.with(context, block);
        //     ErrorEnabled I2 = new ErrorEnabled(context, block);
        if (urlByPass != null) I2.setController(urlByPass);

        block.setWebViewClient(I2);
        if (withVideoElements) {
            block.setWebChromeClient(new ChromeLoader());
            block.getSettings().setJavaScriptEnabled(true);
            block.getSettings().setPluginState(WebSettings.PluginState.ON);
            block.getSettings().setPluginState(WebSettings.PluginState.ON_DEMAND);
        }

        Document doc = Jsoup.parse(template_body);
        doc.body().append(codeing);
        //block.setScrollContainer(false);
        //block.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        String printing = doc.outerHtml();
        Log.d("html", printing);
        block.loadDataWithBaseURL("", printing, "text/html; charset=utf-8", "UTF-8", null);

        block.setVisibility(View.VISIBLE);

        if (callback_webview == null)
            Fx9C.startToReveal(frame_holder, reveal_time);
        else
            Fx9C.startToReveal(frame_holder, reveal_time, callback_webview);
    }


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
            mv.loadDataWithBaseURL("", "", "text/html; charset=utf-8", "UTF-8", null);
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
}
