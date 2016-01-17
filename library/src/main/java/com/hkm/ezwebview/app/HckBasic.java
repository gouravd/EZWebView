package com.hkm.ezwebview.app;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hkm.ezwebview.R;
import com.hkm.ezwebview.webviewleakfix.NonLeakingWebView;

/**
 * Created by zJJ on 1/16/2016.
 */
public abstract class HckBasic extends Fragment {
    protected NonLeakingWebView block;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(LayoutID(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initBinding(view);
        trigger_loading(block);
    }

    protected int LayoutID() {
        return R.layout.hackview;
    }

    @SuppressLint("ResourceAsColor")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    protected void initBinding(View v) {
        block = (NonLeakingWebView) v.findViewById(R.id.wv_content_block);
    }

    protected abstract void trigger_loading(NonLeakingWebView block);
}
