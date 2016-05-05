package com.hkm.ezwebviewsample;


import android.app.Activity;

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

    /**
     * Called when the Fragment is no longer resumed.  This is generally
     * tied to {@link Activity#onPause() Activity.onPause} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onPause() {
        super.onPause();
        if (block != null) {
            block.onPause();
        }
    }

    /**
     * Called when the fragment is visible to the user and actively running.
     * This is generally
     * tied to {@link Activity#onResume() Activity.onResume} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onResume() {
        super.onResume();
        if (block != null) {
            block.onResume();
        }
    }
}
