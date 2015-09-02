package com.hkm.ezwebview.app;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.RelativeLayout;

import com.hkm.ezwebview.R;
import com.hkm.ezwebview.Util.CommentBoxUrl;
import com.hkm.ezwebview.Util.Fx9C;
import com.hkm.ezwebview.Util.In32;
import com.hkm.ezwebview.webviewclients.ChromeLoader;
import com.hkm.ezwebview.webviewclients.FBClient;
import com.hkm.ezwebview.webviewleakfix.NonLeakingWebView;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;

/**
 * Created by hesk on 23/7/15.
 */
public class WebviewCommentBox extends BasicWebView {
    public static final String
            FRAGMENTTITLE_RESID = "title_resid",
            COMMENT_BOX_ID = "comment_box_identification",
            REQUEST_TYPE = "request_type";
    public static final int FB_COMMENT = 9019;
    public static final int SINA_COMMENT = 9016;

    public void kill() {
        killWebViewDefault();
    }

    protected void setup_commentbox(String id) {
        Fx9C.setup_commentbox(this, framer, block, betterCircleBar, id, 1600);
    }

}
