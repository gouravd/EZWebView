package com.hkm.ezwebviewsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hkm.ezwebview.Util.Fx9C;
import com.hkm.ezwebview.app.webviewBaseActivity;
import com.hkm.ezwebviewsample.fragments.JsBridgetFragment;


/**
 * Created by pang on 10/16/17.
 */

public class PopupInfo2 extends webviewBaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.popinfo_area;
    }

    @Override
    protected void start_view() {

        JsBridgetFragment.CartPaymentWebiViewClient webViewClient = new JsBridgetFragment.CartPaymentWebiViewClient(block);
        String str = getIntent().getExtras().getString("url");

        try {
            Fx9C.with()
                    .setWebView(block)
                    .setWebViewClient(webViewClient)
                    .setWebViewHolder(framer)
                    .setProgressBar(betterCircleBar)
                    .setAnimationDuration(1600)
                    .loadUrl(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void init_popup(AppCompatActivity base, String url) {
        Intent in = new Intent(base, PopupInfo2.class);
        // in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Bundle work = new Bundle();
        work.putString("url", url);
        in.putExtras(work);
        base.startActivityForResult(in, 9295);
    }
}
