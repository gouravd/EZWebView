package com.hkm.ezwebviewsample.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hkm.ezwebview.dialog.EzWebDialogNonLeak;
import com.hkm.ezwebview.dialog.EzWebDialogNonLeakv4;
import com.hkm.ezwebviewsample.PopupInfo;
import com.hkm.ezwebviewsample.R;
import com.hkm.ezwebviewsample.YoutubeVid;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;

/**
 * Created by hesk on 9/9/2017.
 */

public class BDialogFragment extends Fragment {


    public static BDialogFragment newInstance() {
        return new BDialogFragment();
    }

    private Button button_one;
    private Button button_only_open_black_screen;
    private Button button_popup_html;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_btn, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button_one = (Button) view.findViewById(R.id.button_one);
        button_only_open_black_screen = (Button) view.findViewById(R.id.button_only_open_black_screen);
        button_popup_html = (Button) view.findViewById(R.id.button_popup_html);
        button_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  EzWebDialogNonLeak.newInstanceYoutube("_Oh9oSZSUbQ").show(getFragmentManager());
                //   YoutubeVid.init_video_youtube_id((AppCompatActivity) getActivity());
                openYT("_Oh9oSZSUbQ");

            }
        });
        button_only_open_black_screen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YoutubeVid.init_video_youtube_id((AppCompatActivity) getActivity());
            }
        });
        button_popup_html.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupInfo.init_popup((AppCompatActivity) getActivity());
            }
        });
    }


    private void openYT(String id) {
        if (getResources().getConfiguration().orientation == ORIENTATION_LANDSCAPE) {
            //YoutubeVid.init_video_youtube_id(this, "_Oh9oSZSUbQ");
            YoutubeVid.init_video_youtube_id((AppCompatActivity) getActivity());
        } else {
            EzWebDialogNonLeak.newInstanceYoutube(id).show(getFragmentManager());
        }
    }
}
