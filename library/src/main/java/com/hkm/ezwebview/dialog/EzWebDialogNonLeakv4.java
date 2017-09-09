package com.hkm.ezwebview.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hkm.ezwebview.R;
import com.hkm.ezwebview.Util.Fx9C;
import com.hkm.ezwebview.Util.In32;
import com.hkm.ezwebview.webviewclients.HClient;
import com.hkm.ezwebview.webviewleakfix.NonLeakingWebView;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;

/**
 * Created by hesk on 9/9/2017.
 */

public class EzWebDialogNonLeakv4 extends DialogFragment implements DialogInterface.OnClickListener {

    protected NonLeakingWebView block;
    protected CircleProgressBar betterCircleBar;
    protected RelativeLayout framer;

    public static EzWebDialogNonLeakv4 newInstanceYoutube(String uri_youtube) {
        EzWebDialogNonLeakv4 ez = new EzWebDialogNonLeakv4();
        Bundle bundle = new Bundle();
        bundle.putString("uri", uri_youtube);
        ez.setArguments(bundle);
        return ez
                ;
    }

    public final void show(FragmentManager fm) {
        setCancelable(false);
        show(fm, "EzWebDialogNonLeakv4");
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        killWebViewDefault();
        dismiss();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View v;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            v = LayoutInflater.from(getContext()).inflate(R.layout.webviewsimple, null, false);
        } else {
            v = LayoutInflater.from(getActivity()).inflate(R.layout.webviewsimple, null, false);
        }

        betterCircleBar = (CircleProgressBar) v.findViewById(R.id.wv_simple_process);
        block = (NonLeakingWebView) v.findViewById(R.id.wv_content_block);
        framer = (RelativeLayout) v.findViewById(R.id.wv_simple_frame);

        setup_video();

        return new AlertDialog.Builder(getActivity())
                .setView(v)




                .setPositiveButton("Close", this)
                // .setNegativeButton("No way", this)



                .create();

    }

    /**
     * This is the example code
     */
    private void setup_video() {

        Bundle ef = getArguments();
        String uri = ef.getString("uri");


        final String embeddedWebContent = In32.fromFileRaw(getActivity(), R.raw.template_youtube_video);
        final String embeddedWebContent_complete = embeddedWebContent.replace("{video_youtube_id}", uri);
        try {
            Fx9C.setup_web_video(
                    this,
                    framer,
                    block,
                    betterCircleBar,
                    embeddedWebContent_complete,
                    new HClient.Callback() {
                        @Override
                        public void retrieveCookie(String cookie_string) {
                            // return In32.interceptURL_cart(url, getAllow(), getInternal(), this);
                        }

                        @Override
                        public boolean overridedefaultlogic(String url, Activity activity) {
                            return false;
                        }
                    },
                    new Runnable() {

                        /**
                         * Starts executing the active part of the class' code. This method is
                         * called when a thread is started that has been created with a class which
                         * implements {@code Runnable}.
                         */
                        @Override
                        public void run() {
                            completeloading();
                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void killWebViewDefault() {
        killWebView(null);
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


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        killWebView(block);
    }

    protected void completeloading() {
        ViewCompat.animate(betterCircleBar).alpha(0f).withEndAction(new Runnable() {
            @Override
            public void run() {
                betterCircleBar.setVisibility(View.GONE);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
