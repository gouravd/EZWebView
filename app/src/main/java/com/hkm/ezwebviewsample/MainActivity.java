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
import com.hkm.ezwebviewsample.fragments.BDialogFragment;
import com.hkm.ezwebviewsample.fragments.CardIoDemoFragment;
import com.hkm.ezwebviewsample.fragments.IstPayment;
import com.hkm.ezwebviewsample.fragments.JsBridgetFragment;
import com.hkm.ezwebviewsample.fragments.OfflineEmbeddedWebViewFragment;
import com.hkm.ezwebviewsample.fragments.OfflineWebViewFragment;
import com.hkm.ezwebviewsample.fragments.ShoppingCartWebViewFragment;
import com.hkm.ezwebviewsample.fragments.VideoWebViewFragment;
import com.hkm.ezwebviewsample.fragments.HpbridFragment;

public class MainActivity extends AppCompatActivity {
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.libVideoFragment: {
                final String code = In32.fromFileRaw(this, R.raw.video_sample);
                ofFragment(VideoFrameBox.with(code, 400));
                break;
            }

            case R.id.libRichTx: {
                final String code = In32.fromFileRaw(this, R.raw.sample_no_video);
                ofFragment(RichTextBox.with(code));
                break;
            }

            case R.id.hybrid: {
                ofFragment(new HpbridFragment());
                break;
            }

            case R.id.textblock: {
                ofFragment(new OfflineWebViewFragment());
                break;
            }

            case R.id.video_classic: {
                ofFragment(new VideoWebViewFragment());
                break;
            }

            case R.id.shopppingcart: {
                ofFragment(new ShoppingCartWebViewFragment());
                break;
            }

            case R.id.offlineSimpleView: {
                ofFragment(new OfflineEmbeddedWebViewFragment());
                break;
            }

            case R.id.card_io_demo: {
                ofFragment(CardIoDemoFragment.newInstance());
                break;
            }
            case R.id.cybersource: {
                ofFragment(IstPayment.newInstance());
                break;
            }
            case R.id.jsbr: {
                ofFragment(JsBridgetFragment.newInstance());
                break;
            }
            case R.id.java_fragment_button: {
                ofFragment(BDialogFragment.newInstance());
                break;
            }

            default: {
                return super.onOptionsItemSelected(item);
            }
        }
        return true;
    }

    protected void ofFragment(Fragment claz) {
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_fcfx, claz, "ClaTrans").addToBackStack(null).commit();
    }
}
