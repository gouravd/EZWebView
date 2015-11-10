package com.hkm.ezwebview.Util;

/**
 * Created by zJJ on 11/10/2015.
 */

import android.net.Uri;

import java.util.*;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UriFactory {

    public static String urlEncodeUTF8(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public static String urlEncodeUTF8(Map<?, ?> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(String.format("%s=%s",
                    urlEncodeUTF8(entry.getKey().toString()),
                    urlEncodeUTF8(entry.getValue().toString())
            ));
        }
        return sb.toString();
    }

    public static Uri androidURLSerialize(String header, Map<?, ?> map) {
        final Uri.Builder bu = Uri.parse(header).buildUpon();
        for (final Map.Entry<?, ?> entry : map.entrySet()) {
            bu.appendQueryParameter(
                    urlEncodeUTF8(entry.getKey().toString()),
                    urlEncodeUTF8(entry.getValue().toString()));
        }
        return bu.build();
    }

    public static String androidURLSerializeStringify(String header, Map<?, ?> map) {
        return androidURLSerialize(header, map).toString();
    }

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("p1", 12);
        map.put("p2", "cat");
        map.put("p3", "a & b");
        System.out.println(urlEncodeUTF8(map));
        System.out.println(androidURLSerializeStringify("http://www.google.com", map));
        // prints "p3=a+%26+b&p2=cat&p1=12"
    }

}
