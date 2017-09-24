package com.hkm.ezwebviewsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hkm.ezwebview.Util.Fx9C;
import com.hkm.ezwebview.Util.In32;
import com.hkm.ezwebview.app.webviewBaseActivity;
import com.hkm.ezwebview.webviewclients.HClient;

/**
 * Created by hesk on 23/9/2017.
 */

public class YoutubeVid extends webviewBaseActivity {
    /**
     * This is the example code
     */
    private void setup_video(String uri) {


        final String embeddedWebContent = In32.fromFileRaw(this, com.hkm.ezwebview.R.raw.template_youtube_video);
        final String embeddedWebContent_complete = embeddedWebContent.replace("{video_youtube_id}", uri);
        try {
            Fx9C.setup_web_video(
                    this,
                    framer,
                    block,
                    betterCircleBar,
                    embeddedWebContent_complete,
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
            e.printStackTrace();
        }

    }


    @Override
    protected void start_view() {

        Bundle ef = getIntent().getExtras();
        String uri = ef.getString("uri");

        setup_video("_Oh9oSZSUbQ");
    }


    public static void init_video_youtube_id(AppCompatActivity base) {
        Intent in = new Intent(base, YoutubeVid.class);
        // in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Bundle work = new Bundle();
        //   work.putString("yid", youtube_id);
        in.putExtras(work);
        base.startActivityForResult(in, 9290);
    }


}
