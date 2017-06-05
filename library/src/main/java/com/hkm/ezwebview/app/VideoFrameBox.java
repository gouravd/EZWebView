package com.hkm.ezwebview.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hkm.ezwebview.Util.Fx9C;
import com.hkm.ezwebview.webviewclients.HClient;

/**
 * Created by hesk on 2/9/15.
 */
public class VideoFrameBox extends BasicWebView {

    public static final String
            EMBEDED = "embeded_code",
            HEIGHT = "pixel_height";

    public static VideoFrameBox with(String embeded_code, int specific_height) {
        Bundle config = new Bundle();
        config.putString(EMBEDED, embeded_code);
        config.putInt(HEIGHT, specific_height);
        final VideoFrameBox newbox = new VideoFrameBox();
        newbox.setArguments(config);
        return newbox;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupVideoBox(
                getArguments().getString(EMBEDED),
                getArguments().getInt(HEIGHT)
        );
    }

    private void setupVideoBox(String code_embeded, int height) {
        //   final String contentc = fromFileRaw(getActivity(), R.raw.video_sample);
        try {
            Fx9C.setup_web_video(
                    this,
                    framer,
                    block,
                    betterCircleBar,
                    code_embeded,
                    height,
                    2000,
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
                           // completeloading();
                        }
                    });

        } catch (Exception e) {
            Log.e("exceptionVideoFrame", e.getMessage());
        }
    }

    public void complete() {
        completeloading();
    }


    /**
     * Called when the fragment is no longer attached to its activity.  This
     * is called after {@link #onDestroy()}.
     */
    @Override
    public void onDetach() {
        super.onDetach();
        killWebViewDefault();
    }

    /**
     * Called when the Fragment is no longer started.  This is generally
     * tied to {@link Activity#onStop() Activity.onStop} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onStop() {
        super.onStop();
        killWebViewDefault();
    }
}
