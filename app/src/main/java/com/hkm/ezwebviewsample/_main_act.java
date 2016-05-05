package com.hkm.ezwebviewsample;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.hkm.ezwebview.Util.In32;
import com.hkm.ezwebview.app.RichTextBox;
import com.hkm.ezwebview.app.VideoFrameBox;

public class _main_act extends AppCompatActivity {
    FragmentTransaction FT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // ofFragment(new shoppingCart());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.libVideoFragment) {
            final String code = In32.fromFileRaw(this, R.raw.video_sample);
            ofFragment(VideoFrameBox.with(code, 400));
            return true;
        } else if (id == R.id.libRichTx) {
            final String code = In32.fromFileRaw(this, R.raw.sample_no_video);
            ofFragment(RichTextBox.with(code));
            return true;
        } else if (id == R.id.hybrid) {
            ofFragment(new hybridfragment());
            return true;
        } else if (id == R.id.textblock) {
            ofFragment(new OfflineWeb());
            return true;
        } else if (id == R.id.video_classic) {
            //video block classic
            ofFragment(new VideoFra());
            return true;
        } else if (id == R.id.comment) {
            ofFragment(new commentboxfragment());
            return true;
        } else if (id == R.id.shopppingcart) {
            ofFragment(new shoppingCart());
            return true;
        } else if (id == R.id.offlineSimpleView) {
            ofFragment(new OfflinePlainWebView());
            return true;
        } else if (id == R.id.js_embeded) {
            ofFragment(new comment_disqus_fragment());
            return true;
        } else if (id == R.id.js_hack) {
            ofFragment(new FbVideoFragment());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void ofFragment(Fragment claz) {
        FT = getFragmentManager().beginTransaction();
        FT.replace(R.id.fragment_fcfx, claz, "ClaTrans").addToBackStack(null).commit();
    }

    protected void test_main() {
        final In32.cssFileListenr f = new In32.cssFileListenr() {
            @Override
            public void readFile(String html_css) {

            }
        };
    }
}
