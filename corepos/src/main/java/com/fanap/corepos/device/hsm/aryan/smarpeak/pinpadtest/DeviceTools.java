package com.fanap.corepos.device.hsm.aryan.smarpeak.pinpadtest;

import android.app.Activity;
import android.util.Log;
import android.view.WindowManager;

import com.pos.sdk.security.PosSecurityManager;

public class DeviceTools {
    public static final String TAG  = "DeviceTools";

    public static void openScreen(Activity activity) {
        Log.e(TAG, "start keep screen on");
        try {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeScreen(Activity activity) {
        Log.e(TAG, "stop keep screen on");
        try {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //enable power button, Menu button, Home button
    public static void power_open() {
        Log.e(TAG,"[powerState] open");
        PosSecurityManager.getDefault().SysSetAppServiceState(false);
    }

    //Disable power button, Menu button, Home button
    public static void power_shut() {
        Log.e(TAG,"[powerState] shut");
        PosSecurityManager.getDefault().SysSetAppServiceState(true);
    }



}
