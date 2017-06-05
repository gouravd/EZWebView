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
