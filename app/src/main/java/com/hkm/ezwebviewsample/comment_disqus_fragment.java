package com.hkm.ezwebviewsample;

import android.os.Bundle;
import android.view.View;

import com.hkm.ezwebview.Util.Fx9C;
import com.hkm.ezwebview.Util.In32;
import com.hkm.ezwebview.app.BasicWebView;

import java.net.URLEncoder;

/**
 * Created by zJJ on 11/9/2015.
 */
public class comment_disqus_fragment extends BasicWebView {
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String template = In32.cssRawName(getActivity(), R.raw.template_disqus_comment);

        /*

        String t1 = template.replace("____REPLACE_URL", "http://hypetrak.com/2015/11/watch-jeezys-tm101-anniversary-documentary");
        String t2 = t1.replace("____DISQUS_TITLE", "big ass tester");
        String t3 = t2.replace("____DISQUS_IDENTIFIER", "293755 http://hypetrak.com/?p=293755");
        String t3 = t2.replace("____DISQUS_FORUM", "hypetrak");

        */


        final StringBuilder query = new StringBuilder();
        query.append("shortname=hypetrak");
        query.append("&url=http://hypetrak.com/2015/11/watch-jeezys-tm101-anniversary-documentary");
        query.append("&identifier=293755 http://hypetrak.com/?p=293755");
        try {
            Fx9C.setup_embedded_js_template(framer, block, betterCircleBar, template, query.toString(), 3000, getActivity());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}