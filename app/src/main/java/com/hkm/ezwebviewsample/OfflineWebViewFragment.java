package com.hkm.ezwebviewsample;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RawRes;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.hkm.ezwebview.Util.Fx9C;
import com.hkm.ezwebview.Util.In32;
import com.hkm.ezwebview.webviewclients.HClient;
import com.hkm.ezwebview.webviewleakfix.NonLeakingWebView;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;

import java.util.Scanner;

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
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.webviewarticle, container, false);
    }

    private static String fromFileRaw(Context ctx, final @RawRes int resource_raw_file_name) {
        StringBuilder sb = new StringBuilder();
        Scanner s = new Scanner(ctx.getResources().openRawResource(resource_raw_file_name));
        while (s.hasNextLine()) {
            sb.append(s.nextLine() + "\n");
        }
        return sb.toString();
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onViewCreated(View v, Bundle b) {
        initBinding(v);
        final String contentc = fromFileRaw(getActivity(), R.raw.sample_no_video);
        try {
            Fx9C.setup_content_block_wb(this,
                    content_article_frame,
                    block,
                    contentc,
                    new Runnable() {
                        @Override
                        public void run() {
                            ViewCompat.animate(mprogressbar).alpha(0f).withEndAction(new Runnable() {
                                @Override
                                public void run() {
                                    mprogressbar.setVisibility(View.GONE);
                                }
                            });
                        }
                    });
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
