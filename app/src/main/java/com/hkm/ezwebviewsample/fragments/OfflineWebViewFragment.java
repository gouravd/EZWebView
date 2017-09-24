package com.hkm.ezwebviewsample.fragments;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.hkm.ezwebview.Util.Fx9C;
import com.hkm.ezwebview.Util.In32;
import com.hkm.ezwebview.webviewclients.ChromeLoader;
import com.hkm.ezwebview.webviewclients.HClient;
import com.hkm.ezwebview.webviewleakfix.NonLeakingWebView;
import com.hkm.ezwebviewsample.R;


import java.util.HashMap;

import static com.hkm.ezwebview.Util.In32.loadRawResWithCss;

/**
 * Created by hesk on 6/8/15.
 */
public class OfflineWebViewFragment extends Fragment {

    private ProgressBar mprogressbar;
    private NonLeakingWebView block;
    private RelativeLayout content_article_frame;

    public OfflineWebViewFragment() {
    }

    @SuppressLint("ResourceAsColor")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    protected void initBinding(View v) {
        block = (NonLeakingWebView) v.findViewById(R.id.content_block);
        mprogressbar = (ProgressBar) v.findViewById(R.id.progressc);
        content_article_frame = (RelativeLayout) v.findViewById(R.id.content_article_frame);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.webviewarticle, container, false);
    }


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onViewCreated(View v, Bundle b) {
        initBinding(v);
        final HashMap<String, String> data = In32.getHTMLparamsBase();
        data.put("remarker_data", "this is the sample remark ......");

        try {

            block.getSettings().setJavaScriptEnabled(true);

            Fx9C
                    .with()
                    .setProgressBar(mprogressbar)
                    .setAnimationDuration(1600)
                    .setWebViewClient(new HClient(getActivity(), block))
                    .setWebViewHolder(content_article_frame)
                    .setWebView(block)
                    .loadContentWithRawHTMLWithCssData(R.raw.instruction_content, R.raw.instruction_css, data);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (block != null) {
            block.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (block != null) {
            block.onResume();
        }
    }

}
