package com.hkm.ezwebviewsample;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RawRes;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hkm.ezwebview.Util.Fx9C;
import com.hkm.ezwebview.Util.In32;
import com.hkm.ezwebview.webviewclients.HClient;
import com.hkm.ezwebview.webviewleakfix.NonLeakingWebView;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by hesk on 1/9/15.
 */
public class VideoWebViewFragment extends Fragment {

    private NonLeakingWebView block;
    private CircleProgressBar betterCircleBar;
    private RelativeLayout framer;

    private List<String> getInternal() {
        final List<String> h = new ArrayList<>();

        return h;
    }

    private List<String> getAllow() {
        final List<String> h = new ArrayList<>();
        h.add("techcrunch.com");
        h.add("google.com");
        h.add("google.com.hk");
        return h;
    }

    protected int get_layout_id() {
        return R.layout.videof;
    }


    @SuppressLint("ResourceAsColor")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    protected void initBinding(View v) {
        betterCircleBar = (CircleProgressBar) v.findViewById(R.id.progressloadingbarpx);
        block = (NonLeakingWebView) v.findViewById(R.id.videoplayer);
        framer = (RelativeLayout) v.findViewById(R.id.framevideoplayer);
    }

    private void killWebView(NonLeakingWebView mWebView) {
        //http://stackoverflow.com/questions/3815090/webview-and-html5-video
        if (mWebView.getVisibility() == View.GONE) {
            mWebView.loadUrl("about:blank");
            mWebView.destroy();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(get_layout_id(), container, false);
    }


    protected void completeloading() {
        ViewCompat.animate(betterCircleBar).alpha(0f).withEndAction(new Runnable() {
            @Override
            public void run() {
                betterCircleBar.setVisibility(View.GONE);
            }
        });
    }

    /**
     * Called when the Fragment is no longer resumed.  This is generally
     * tied to {@link Activity#onPause() Activity.onPause} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onPause() {
        super.onPause();
        if (block != null) {
            block.onPause();
        }
    }

    /**
     * Called when the fragment is visible to the user and actively running.
     * This is generally
     * tied to {@link Activity#onResume() Activity.onResume} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onResume() {
        super.onResume();
        if (block != null) {
            block.onResume();
        }
    }

    public void kill() {
        killWebView(block);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initBinding(view);
        setup_video();
    }

    /**
     * This is the example code
     */
    private void setup_video() {
        final String embeddedWebContent = In32.fromFileRaw(getActivity(), R.raw.video_sample);
        try {
            Fx9C.setup_web_video(
                    this,
                    framer,
                    block,
                    betterCircleBar,
                    embeddedWebContent,
                    new HClient.Callback() {
                        @Override
                        public void retrieveCookie(String cookie_string) {
                            // return In32.interceptURL_cart(url, getAllow(), getInternal(), this);
                        }

                        @Override
                        public boolean overridedefaultlogic(String url, Activity activity) {
                            return false;
                        }
                    },
                    new Runnable() {

                        /**
                         * Starts executing the active part of the class' code. This method is
                         * called when a thread is started that has been created with a class which
                         * implements {@code Runnable}.
                         */
                        @Override
                        public void run() {

                        }
                    });

        } catch (Exception e) {
        }

    }

}
