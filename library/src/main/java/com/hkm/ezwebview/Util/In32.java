package com.hkm.ezwebview.Util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RawRes;
import android.util.Log;

import com.hkm.ezwebview.R;
import com.hkm.ezwebview.webviewclients.URLClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

/**
 * Created by hesk on 6/8/15.
 */
public class In32 {

    private static String APP_INTENT_TITLE = "title";
    private static String APP_INTENT_URI = "uri";

    /**
     * start the new activities
     *
     * @param packageName the package application id
     * @param url         the url to start from
     * @param title       the title to send as extra information
     * @param activity    the activity
     */
    public static void startNewActivity(final String packageName, final String url, final String title, final Context activity) {
        Intent intent = activity.getPackageManager().getLaunchIntentForPackage(packageName);
        if (intent != null) {
        /* We found the activity now start the activity */
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Bundle b = new Bundle();
            b.putString(APP_INTENT_URI, url);
            b.putString(APP_INTENT_TITLE, title);
            intent.putExtras(b);
            activity.startActivity(intent);
        } else {
        /* Bring user to the market or let them choose an app? */
            intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("market://details?id=" + packageName));
            activity.startActivity(intent);
        }
    }

    public static boolean hasNoVideoElement(String txt) {
        return txt.lastIndexOf("iframe") == -1 && txt.lastIndexOf("IFRAME") == -1;
    }

    public static String cssByContentPost(Context context) {
        return cssframework(context, R.raw.popbee_v6);
    }

    public static String cssByVideo(Context context) {
        return cssframework(context, R.raw.videoconfig);
    }

    public static String cssRawName(Context context, @RawRes int resId) {
        return cssframework(context, resId);
    }

    /**
     * enable css framework from the css file setup
     *
     * @param ctx                    the context from the resources
     * @param resource_raw_file_name the file
     * @return he string
     */
    private static String cssframework(Context ctx, final @RawRes int resource_raw_file_name) {
        StringBuilder sb = new StringBuilder();
        Scanner s = new Scanner(ctx.getResources().openRawResource(resource_raw_file_name));
        sb.append("<style type=\"text/css\">");
        while (s.hasNextLine()) {
            sb.append(s.nextLine() + "\n");
        }
        sb.append("</style>");
        return sb.toString();
    }

    private static String[] getSegments(final Uri base) {
        String[] segments = base.getPath().split("/");
        String token = base.getLastPathSegment();
        return segments;
    }


    /**
     * start the application in browser to see the url or choose by other application to view this uri
     *
     * @param url      in full path for url
     * @param activity the activity
     */
    public static void openOtherUri(final String url, final Activity activity) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        activity.startActivity(intent);
    }

    public static boolean interceptURL_cart(final String url, List<String> allowing, List<String> startfrom, URLClient.cb cb) {
        for (final String urli : allowing) {
            if (Uri.parse(url).getHost().endsWith(urli)) {
                return false;
            }
        }
        for (final String urli : startfrom) {
            if (urli.startsWith(url)) {
                cb.triggerNative(Uri.parse(url));
                return true;
            }
        }
        return true;
    }

    public static boolean interceptURL_HB(String url, Activity activity) {
        if (Uri.parse(url).getHost().endsWith("store.hypebeast.com")) {
            String[] list = getSegments(Uri.parse(url));
            boolean brand = list[1].equalsIgnoreCase("brands");
            String brandname = list[2];
            startNewActivity("com.hypebeast.store", url, brandname, activity);
            return true;
        } else if (Uri.parse(url).getHost().endsWith("hypebeast.com")) {
            String[] list = getSegments(Uri.parse(url));
            String g = list[1];
            if (g.equalsIgnoreCase("tags")) {

                return true;
            } else {
                //  PBUtil.startNewArticle(url, activity);
                return true;
            }
        } else if (Uri.parse(url).getHost().length() == 0) {
            return true;
        }
        openOtherUri(url, activity);
        return true;
    }

    /**
     * read data from the resource folder in the package
     *
     * @param ctx                    the context
     * @param resource_raw_file_name the raw file name
     * @return the string in return
     */
    public static String fromFileRaw(Context ctx, final @RawRes int resource_raw_file_name) {
        StringBuilder sb = new StringBuilder();
        Scanner s = new Scanner(ctx.getResources().openRawResource(resource_raw_file_name));
        while (s.hasNextLine()) {
            sb.append(s.nextLine() + "\n");
        }
        return sb.toString();
    }

    public interface loadListener {
        void readFile(String completed_text);
    }

    public static class cssFileListenr implements In32.loadListener {
        public cssFileListenr() {
        }

        @Override
        public void readFile(String html_css) {

        }
    }

    public static void loadFromLocalFileText(String folder_name, String file_name, final loadListener action) throws IOException {
        String root = Environment.getExternalStorageDirectory().toString() + File.separator;
        File myDir = new File(root + folder_name + File.separator + file_name);
        loadFromLocalFileText(myDir, action);
    }

    public static void loadFromLocalFileText(String full_path_cachedFile, final loadListener action) throws IOException {
        File myDir = new File(full_path_cachedFile);
        loadFromLocalFileText(myDir, action);
    }

    public static void loadFromLocalFileText(File cachedFile, final loadListener read_done) throws IOException {
        String UTF8 = "utf8";
        int BUFFER_SIZE = 8192;

        final BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(cachedFile), UTF8), BUFFER_SIZE);


        new AsyncTask<Void, Void, Void>() {
            protected String temp_line;
            protected StringBuilder sb = new StringBuilder();

            protected String line() throws IOException {
                temp_line = br.readLine();
                return temp_line;
            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    while (line() != null) {
                        sb.append(temp_line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }


            @Override
            protected void onPostExecute(Void aVoid) {
                if (read_done != null) {
                    if (read_done instanceof cssFileListenr) {
                        StringBuilder sbl = new StringBuilder();
                        sbl.append("<style type=\"text/css\">");
                        sbl.append(sb.toString());
                        sbl.append("</style>");
                        read_done.readFile(sbl.toString());
                    } else {
                        read_done.readFile(sb.toString());
                    }
                }
            }
        }.execute();


    }


}
