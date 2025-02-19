package com.fanap.corepos.device;

import android.os.Build;
import android.util.Log;

public class DeviceUtils {


    public static String getDeviceName() {
//        TODO NEWLAND
//        String manufacturer = Build.MANUFACTURER;
//        TODO TIANYU
        String manufacturer = Build.PRODUCT;
        String model = Build.MODEL;
        Log.e("TAGA","model:  "+ model);
        Log.e("TAGA","name-:  "+ manufacturer);
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    private static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }
}
