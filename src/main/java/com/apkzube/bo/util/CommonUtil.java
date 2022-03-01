package com.apkzube.bo.util;

public class CommonUtil {

    public static boolean isValidURL(String url) {
        boolean b = false;

        if (url != null && !url.isBlank()) {
            if (url.toLowerCase().contains("http")) {
                b = true;
            }
        }
        return b;
    }
}
