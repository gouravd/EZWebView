package com.hkm.ezwebview.Util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by hesk on 6/8/15.
 */
public class CommentBoxUrl {
    public static String sinaCommentbox(final String url_comment_box_id) {
        String template = "http://widget.weibo.com/distribution/comments.php?width=0&url=____COMMENTBOX___URL__ID____&appkey=3872527314&dpc=1";
        template = template.replace("____COMMENTBOX___URL__ID____", url_comment_box_id);
        //Log.d("webview", template);
        return template;
    }

    public static String sampleFacebookCommentBox() {
        String templatecc = "https://www.facebook.com/plugins/comments.php?api_key=187288694643718&channel_url=http%3A%2F%2Fstatic.ak.facebook.com%2Fconnect%2Fxd_arbiter%2F44OwK74u0Ie.js%3Fversion%3D41%23cb%3Df2848f81b4%26domain%3Dtechcrunch.com%26origin%3Dhttp%253A%252F%252Ftechcrunch.com%252Ff1b81b2c9%26relation%3Dparent.parent&href=http%3A%2F%2Ftechcrunch.com%2F2015%2F08%2F27%2Fteslas-model-s-p85d-just-broke-consumer-reports-ratings-system-scoring-103-out-of-100%2F&locale=en_US&numposts=25&sdk=joey&version=v2.1&width=100%25";

        return templatecc;
    }

    public static String popbeeCommentBox(final String url_comment_box_id) {
        String template = "https://www.facebook.com/plugins/comments.php?api_key=155803211143175&channel_url=http%3A%2F%2Fstatic.ak.facebook.com%2Fconnect%2Fxd_arbiter%2FX9pYjJn4xhW.js%3Fversion%3D41%23cb%3Df302928438%26domain%3Dpopbee.com%26origin%3Dhttp%253A%252F%252Fpopbee.com%252Ff3a9bd48c%26relation%3Dparent.parent&colorscheme=light&href=____COMMENTBOX___URL__ID____&locale=en_US&numposts=5&sdk=joey&skin=light&version=v2.3&width=100%25";
        try {
            template = template.replace("____COMMENTBOX___URL__ID____", URLEncoder.encode(url_comment_box_id, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //Log.d("webview", template);
        return template;
    }
}
