package com.hkm.ezwebview.app;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;

import com.hkm.ezwebview.R;
import com.hkm.ezwebview.loadingi.CircleProgressBar;
import com.hkm.ezwebview.webviewleakfix.NonLeakingWebView;

/**
 * Created by hesk on 23/9/2017.
 */

public abstract class webviewBaseActivity extends AppCompatActivity {


    protected NonLeakingWebView block;
    protected CircleProgressBar betterCircleBar;
    protected RelativeLayout framer;


    protected int getLayoutId() {
        return R.layout.video_frame;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        betterCircleBar = (CircleProgressBar) findViewById(com.hkm.ezwebview.R.id.wv_simple_process);
        block = (NonLeakingWebView) findViewById(com.hkm.ezwebview.R.id.wv_content_block);
        framer = (RelativeLayout) findViewById(com.hkm.ezwebview.R.id.wv_simple_frame);
        start_view();
    }

    protected abstract void start_view();

    protected void completeloading() {
        ViewCompat.animate(betterCircleBar).alpha(0f).withEndAction(new Runnable() {
            @Override
            public void run() {
                betterCircleBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        // killWebViewLowMemory(null);
    }

    protected void killWebViewLowMemory(NonLeakingWebView mWebView) {
        //http://stackoverflow.com/questions/3815090/webview-and-html5-video
        if (mWebView == null) {
            if (block.getVisibility() != View.GONE && block != null) {
                block.setVisibility(View.GONE);
                block.loadUrl("about:blank");
                block.destroy();
            }
        } else if (mWebView.getVisibility() != View.GONE) {
            mWebView.setVisibility(View.GONE);
            mWebView.loadUrl("about:blank");
            mWebView.destroy();
        }
    }

    protected void killWebView(NonLeakingWebView mWebView) {
        //http://stackoverflow.com/questions/3815090/webview-and-html5-video
        if (mWebView == null) {
            if (block.getVisibility() != View.GONE && block != null) {
                block.setVisibility(View.GONE);
                block.loadUrl("about:blank");
            }
        } else if (mWebView.getVisibility() != View.GONE) {
            mWebView.setVisibility(View.GONE);
            mWebView.loadUrl("about:blank");
        }
    }

    protected void killWebViewDefault() {
        killWebView(null);
    }
}
