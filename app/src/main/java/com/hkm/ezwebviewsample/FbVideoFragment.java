package com.hkm.ezwebviewsample;


import com.hkm.ezwebview.app.HckBasic;
import com.hkm.ezwebview.webviewleakfix.NonLeakingWebView;


/**
 * Created by zJJ on 1/16/2016.
 */
public class FbVideoFragment extends HckBasic {

    @Override
    protected void trigger_loading(NonLeakingWebView block) {
   /*     JsEvaluator jsEvaluator = new JsEvaluator(getActivity());

        Fx9C.setup_hack_view(
                block,
                new HackContentClient(getActivity(), new HackContentClient.LoadListener() {
                    @Override
                    public void processHTML(String html) {
                        Log.e("hackresult", html);
                        ErrorMessage.alert(html, getChildFragmentManager());
                    }
                }),

                "https://m.facebook.com/story.php?story_fbid=10154509260141038&id=13062516037"

        );
        block.setVisibility(View.VISIBLE);
*/

    }


}
