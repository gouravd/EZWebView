package com.hkm.ezwebviewsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hkm.ezwebview.Util.Fx9C;
import com.hkm.ezwebview.Util.In32;
import com.hkm.ezwebview.app.webviewBaseActivity;
import com.hkm.ezwebviewsample.fragments.JsBridgetFragment;

import java.util.HashMap;

/**
 * Created by hesk on 23/9/2017.
 */

public class PopupInfo extends webviewBaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.full_height_nonleakview;
    }

    @Override
    protected void start_view() {
        final HashMap<String, String> data = In32.getHTMLparamsBase();
        data.put("remarker_data", "this is the sample remark ......");
        JsBridgetFragment.CartPaymentWebiViewClient webViewClient = new JsBridgetFragment.CartPaymentWebiViewClient(block);


        try {
            Fx9C.with()
                    .setWebView(block)
                    .setWebViewClient(webViewClient)
                    .setWebViewHolder(framer)
                    .setProgressBar(betterCircleBar)
                    .setAnimationDuration(1600)
                    .loadContentWithRawHTMLWithCssData(R.raw.instruction_content, R.raw.instruction_css, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void init_popup(AppCompatActivity base) {
        Intent in = new Intent(base, PopupInfo.class);
        // in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Bundle work = new Bundle();
        //   work.putString("yid", youtube_id);
        in.putExtras(work);
        base.startActivityForResult(in, 9290);
    }
}
