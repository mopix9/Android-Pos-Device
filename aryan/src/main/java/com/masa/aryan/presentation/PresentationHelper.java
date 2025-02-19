package com.masa.aryan.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.display.DisplayManager;
import android.net.Uri;
import android.provider.Settings;
import android.view.Display;

/**
 * Created by @author chencz on 2018/7/17.
 */

public class PresentationHelper {

    /**
     * 系统版本是否支持分屏
     *
     * Android 4.2 开始支持
     */
    public static boolean isSupportPresentation(){
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {//17
            return false;
        }
        return true;
    }

    /**
     * 是否已经授权（这里需要悬浮窗权限）
     *
     * 在大于等于23版本下编译，悬浮窗权限默认是关闭没有权限，然在小于23版本下编译悬浮窗权限是开启有权限的。所以在大于23版本下编译时需要去检测悬浮窗权限，并且获取悬浮窗权限
     */
    public static boolean isPermitForPresentation(Context context){
        boolean permit = true;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {//23
            permit = Settings.canDrawOverlays(context);
        }
        return permit;
    }

    /**
     * 获取display列表
     */
    public static Display[] getDisplayList(Context context){
        DisplayManager mDisplayManager = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
        Display[] displays = mDisplayManager.getDisplays();
        return displays;
    }

    /**
     * 是否有副屏
     */
    public static boolean hasOtherDisplay(Context context){
        // displays[0] 主屏
        // displays[1] 副屏
        Display[] displays = getDisplayList(context);
        if(null != displays && displays.length > 1){
            return true;
        }
        return false;
    }

    /**
     * 请求授权
     *
     * 在大于等于23版本下编译，悬浮窗权限默认是关闭没有权限，然在小于23版本下编译悬浮窗权限是开启有权限的。
     * 所以在大于等于23版本下编译时需要去检测悬浮窗权限，并且获取悬浮窗权限
     *
     * 通过startActivityForResult跳转到授权页，使用requestCode
     * 调用页面需要在页面中onActivityResult处理
     * @param context
     * @param requestCode
     */
    public static void requestPermitForPresentation(Activity context,int requestCode){
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
        context.startActivityForResult(intent, requestCode);
    }

}
