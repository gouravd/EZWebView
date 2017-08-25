package com.hkm.ezwebview.Util;

/**
 * Created by zJJ on 11/10/2015.
 */

import android.net.Uri;

import java.net.URLDecoder;
import java.util.*;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class UriFactory {

    public final static String urlEncodeUTF8(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }

    public final static String urlEncodeUTF8(Map<?, ?> map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(String.format("%s=%s", urlEncodeUTF8(entry.getKey().toString()), urlEncodeUTF8(entry.getValue().toString())));
        }
        return sb.toString();
    }

    public final static Uri androidURLSerialize(String starting_uri, Map<?, ?> map) {
        final Uri.Builder bu = Uri.parse(starting_uri).buildUpon();
        for (final Map.Entry<?, ?> entry : map.entrySet()) {
            bu.appendQueryParameter(
                    urlEncodeUTF8(entry.getKey().toString()),
                    urlEncodeUTF8(entry.getValue().toString()));
        }
        return bu.build();
    }

    public final static String androidURLSerializeStringify(String header, Map<?, ?> map) {
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


    public final static String mapToString(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String key : map.keySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            String value = map.get(key);
            try {
                stringBuilder.append((key != null ? URLEncoder.encode(key, "UTF-8") : ""));
                stringBuilder.append("=");
                stringBuilder.append(value != null ? URLEncoder.encode(value, "UTF-8") : "");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("This method requires UTF-8 encoding support", e);
            }
        }

        return stringBuilder.toString();
    }

    public final static Map<String, String> stringToMap(String input) {
        Map<String, String> map = new HashMap<String, String>();

        String[] nameValuePairs = input.split("&");
        for (String nameValuePair : nameValuePairs) {
            String[] nameValue = nameValuePair.split("=");
            try {
                map.put(URLDecoder.decode(nameValue[0], "UTF-8"), nameValue.length > 1 ? URLDecoder.decode(
                        nameValue[1], "UTF-8") : "");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("This method requires UTF-8 encoding support", e);
            }
        }

        return map;

    }

}
