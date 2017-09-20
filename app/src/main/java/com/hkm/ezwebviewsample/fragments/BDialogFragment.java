package com.hkm.ezwebviewsample.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hkm.ezwebview.dialog.EzWebDialogNonLeak;
import com.hkm.ezwebviewsample.R;

/**
 * Created by hesk on 9/9/2017.
 */

public class BDialogFragment extends Fragment {


    public static BDialogFragment newInstance() {
        return new BDialogFragment();
    }

    Button button_one;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_btn, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button_one = (Button) view.findViewById(R.id.button_one);
        button_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EzWebDialogNonLeak.newInstanceYoutube("_Oh9oSZSUbQ").show(getFragmentManager());

            }
        });
    }
}
