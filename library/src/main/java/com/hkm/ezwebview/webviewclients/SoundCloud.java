package com.hkm.ezwebview.webviewclients;

import android.app.Activity;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;

import com.hkm.ezwebview.webviewleakfix.PreventLeakClient;

/**
 * Created by hesk on 21/8/15.
 */
public class SoundCloud extends WebChromeClient {
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        String header = "JSON Data: ";
        String url = consoleMessage.message().substring(header.length(), -1);
        return true;
    }
}
