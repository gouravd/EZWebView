package com.hkm.ezwebview.webviewclients;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.hkm.ezwebview.webviewleakfix.PreventLeakClientChrome;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;

/**
 * Created by hesk on 3/13/15.
 */
public class ChromeLoader extends PreventLeakClientChrome {
    private CircleProgressBar mCircleProgressBar;
    private boolean control_webview_show_hide_onload = false;
    private Activity mActivity;

    private ProgressBar cpb;
    private boolean withLoadingText = false;
    private String loadingText;
    private CharSequence barTitle;
    private int time_of_fade = 0;

    public ChromeLoader(CircleProgressBar circlebar, int time_fade) {
        this(circlebar);
        time_of_fade = time_fade;
    }

    public ChromeLoader(CircleProgressBar circlebar) {
        mCircleProgressBar = circlebar;
        mCircleProgressBar.setCircleBackgroundEnabled(true);
        mCircleProgressBar.setVisibility(View.VISIBLE);
        mCircleProgressBar.setShowProgressText(true);
    }

    public ChromeLoader(Activity c) {
        mActivity = c;
        mActivity.setProgressBarVisibility(true);
        barTitle = c.getTitle();
    }

    public ChromeLoader() {

    }

    public ChromeLoader(Activity c, ProgressBar bar) {
        cpb = bar;
        mActivity = c;
        barTitle = c.getTitle();
    }

    public ChromeLoader setShowHideWebViewOnload(boolean b) {
        control_webview_show_hide_onload = b;
        return this;
    }

    public ChromeLoader setLoadingText(String b) {
        loadingText = b;
        withLoadingText = true;
        return this;
    }

    @Override
    public void onProgressChanged(WebView view, int progress) {
        if (mCircleProgressBar != null) {
            if (progress < 100) {
                mCircleProgressBar.setProgress(progress);
                if (mCircleProgressBar.getVisibility() == View.GONE) {
                    mCircleProgressBar.setVisibility(View.VISIBLE);
                }
                if (control_webview_show_hide_onload && view.getVisibility() == View.VISIBLE)
                    view.setVisibility(View.GONE);
            } else {
                if (time_of_fade > 0) {
                    ViewCompat.animate(mCircleProgressBar).alpha(0f).withEndAction(new Runnable() {
                        @Override
                        public void run() {
                            mCircleProgressBar.setVisibility(View.GONE);
                        }
                    });
                } else {
                    mCircleProgressBar.setVisibility(View.GONE);
                }

                if (control_webview_show_hide_onload && view.getVisibility() == View.GONE)
                    view.setVisibility(View.VISIBLE);
            }

        } else if (mActivity != null) {
            //mActivity.setProgressBarIndeterminateVisibility(true);
            cpb.setVisibility(View.VISIBLE);
            if (withLoadingText) {
                mActivity.setTitle(loadingText);
            }
            cpb.setProgress(progress);
            if (control_webview_show_hide_onload && view.getVisibility() == View.VISIBLE)
                view.setVisibility(View.GONE);
            if (progress == 100) {
                if (withLoadingText) {
                    if (barTitle == null)
                        mActivity.setTitle("no name");
                    else mActivity.setTitle(barTitle);
                }
                cpb.setVisibility(View.GONE);
                if (control_webview_show_hide_onload && view.getVisibility() == View.GONE)
                    view.setVisibility(View.VISIBLE);
            }
        }

    }

}
