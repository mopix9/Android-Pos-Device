package com.fanap.corepos.device.install;

public interface InstallInterface {
    /*
     * param beepType : Utils.BEEP_TYPE_SUCCESS
     */
    public boolean installApk(String apkAddress);
}
