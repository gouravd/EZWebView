package com.hkm.ezwebview.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hkm.ezwebview.Util.Fx9C;
import com.hkm.ezwebview.Util.In32;
import com.hkm.ezwebview.webviewclients.HClient;

/**
 * Created by hesk on 2/9/15.
 */
public class RichTextBox extends BasicWebView {
    public static final String
            HASMEDIAEMBED = "EMBED_ENABLED",
            HTML5TEXT = "html";

    public static RichTextBox with(String embeded_code) {
        Bundle config = new Bundle();
        config.putString(HTML5TEXT, embeded_code);
        config.putBoolean(HASMEDIAEMBED, !In32.hasNoVideoElement(embeded_code));
        final RichTextBox newbox = new RichTextBox();
        newbox.setArguments(config);
        return newbox;
    }

    private boolean hasVideo;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupContentBox(
                getArguments().getString(HTML5TEXT),
                hasVideo = getArguments().getBoolean(HASMEDIAEMBED)
        );
    }


    private void setupContentBox(String code_embeded, boolean watchVideoEnabled) {
        //   final String contentc = fromFileRaw(getActivity(), R.raw.video_sample);
        try {
            Fx9C.setup_content_block_wb(
                    this,
                    framer,
                    block,
                    code_embeded,
                    watchVideoEnabled,
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
                            completeloading();
                        }
                    });

        } catch (Exception e) {
            Log.e("exceptionVideoFrame", e.getMessage());
        }
    }


    /**
     * Called when the view previously created by {@link #onCreateView} has
     * been detached from the fragment.  The next time the fragment needs
     * to be displayed, a new view will be created.  This is called
     * after {@link #onStop()} and before {@link #onDestroy()}.  It is called
     * <em>regardless</em> of whether {@link #onCreateView} returned a
     * non-null view.  Internally it is called after the view's state has
     * been saved but before it has been removed from its parent.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    /**
     * Called when the fragment is no longer attached to its activity.  This
     * is called after {@link #onDestroy()}.
     */
    @Override
    public void onDetach() {
        super.onDetach();
        if (hasVideo) killWebViewDefault();
    }

    /**
     * Called when the Fragment is no longer started.  This is generally
     * tied to {@link Activity#onStop() Activity.onStop} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onStop() {
        super.onStop();
        if (hasVideo) killWebViewDefault();
    }
}
