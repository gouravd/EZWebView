package com.hkm.ezwebviewsample;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.hkm.ezwebview.Util.In32;
import com.hkm.ezwebview.app.RichTextBox;

/**
 * Created by hesk on 2/9/15.
 */
public class OfflineFragments extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.part_fragment, container, false);
    }

    private RichTextBox view1, view2;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final ScrollView scrollView = (ScrollView) view.findViewById(R.id.scroller_container);
        final String novideocode = In32.fromFileRaw(getActivity(), R.raw.sample_no_video);
        final String videocode = In32.fromFileRaw(getActivity(), R.raw.sample_html);

        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.frame_ly1,
                        RichTextBox
                                .with(novideocode)
                        ,
                        "novideo")
                .addToBackStack(null)
                .commit();

        getChildFragmentManager().
                beginTransaction()
                .add(R.id.frame_ly2,
                        RichTextBox
                                .with(videocode)
                        ,
                        "hasvideo")
                .addToBackStack(null)
                .commit();

    }
}
