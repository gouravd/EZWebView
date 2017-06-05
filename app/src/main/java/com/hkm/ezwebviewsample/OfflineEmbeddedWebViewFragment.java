package com.hkm.ezwebviewsample;

import android.os.Bundle;
import android.view.View;

import com.hkm.ezwebview.Util.Fx9C;
import com.hkm.ezwebview.Util.In32;
import com.hkm.ezwebview.app.BasicWebViewNormal;

/**
 * Created by hesk on 5/10/15.
 */
public class OfflineEmbeddedWebViewFragment extends BasicWebViewNormal {

    public static final String n_content = "Model's height: 6'2\"/186cm, chest 37\"/95cm, waist 30\"/76cm, hips 34\"/86cm.\r\nHe's wearing a medium.\r\n<table> <thead> <tr> \r\n<th>Size</th> \r\n<th>Chest</th>\r\n <th>Length</th>\r\n <th>Sleeve</th>\r\n</tr> \r\n<tr> <td>S</td>\r\n<td>40”/101.6cm</td> \r\n<td>27.5”/69.8cm</td> \r\n<td>9”/22.9cm</td> \r\n</tr>\r\n <tr> <td>M</td>\r\n <td>42”/106.7cm</td> \r\n<td>28.5”/72.4cm</td>\r\n <td>9.5”/24.1cm</td> \r\n</tr>\r\n <tr> <td>L</td> \r\n<td>44”/111.8cm</td> \r\n<td>29.5”/74.9cm</td> \r\n<td>10”/25.4cm</td>\r\n </tr> <tr> \r\n<td>XL</td> \r\n<td>47”/119cm</td> \r\n<td>30.5”/77.5cm</td>\r\n<td>10.5”/26.7cm</td> \r\n </tr> <tr> </tbody> </table>";

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Fx9C.setup_content_block_wb(framer, block, betterCircleBar, In32.cssRawName(getActivity(), R.raw.table_hb_css) + n_content, 1600);
    }
}
