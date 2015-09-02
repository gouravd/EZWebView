package com.hkm.ezwebviewsample;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.hkm.ezwebview.Util.In32;
import com.hkm.ezwebview.app.RichTextBox;
import com.hkm.ezwebview.app.VideoFrameBox;

public class MainActivity extends AppCompatActivity {
    FragmentTransaction FT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FT = getFragmentManager().beginTransaction();
        FT.add(R.id.fragment_fcfx, new OfflineFragments(), "offlinemainfragment")
                .addToBackStack(null).commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.libVideoFragment) {
            final String code = In32.fromFileRaw(this, R.raw.video_sample);
            FT = getFragmentManager().beginTransaction();
            FT.replace(R.id.fragment_fcfx, VideoFrameBox.with(code, 400), "EGG").addToBackStack(null).commit();
            return true;
        }
        if (id == R.id.libRichTx) {
            final String code = In32.fromFileRaw(this, R.raw.sample_no_video);
            FT = getFragmentManager().beginTransaction();
            FT.replace(R.id.fragment_fcfx, RichTextBox.with(code), "EGG").addToBackStack(null).commit();
            return true;
        } else if (id == R.id.hybrid) {
            FT = getFragmentManager().beginTransaction();
            FT.replace(R.id.fragment_fcfx, new hybridfragment(), "TA").addToBackStack(null).commit();
            return true;
        } else if (id == R.id.textblock) {
            FT = getFragmentManager().beginTransaction();
            FT.replace(R.id.fragment_fcfx, new OfflineWeb(), "FV").addToBackStack(null).commit();
            return true;
        } else if (id == R.id.video) {
            FT = getFragmentManager().beginTransaction();
            FT.replace(R.id.fragment_fcfx, new VideoFra(), "EGC").addToBackStack(null).commit();
            return true;
        } else if (id == R.id.comment) {
            FT = getFragmentManager().beginTransaction();
            FT.replace(R.id.fragment_fcfx, new commentboxfragment(), "EGG").addToBackStack(null).commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
