package com.hkm.ezwebview.app;
import com.hkm.ezwebview.Util.Fx9C;
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
