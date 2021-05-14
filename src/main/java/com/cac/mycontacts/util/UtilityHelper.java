package com.cac.mycontacts.util;

import java.net.URL;

public class UtilityHelper {
    private UtilityHelper() {

    }

    public static boolean isValidUrl(String url)
    {
        try {
            new URL(url).toURI();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
