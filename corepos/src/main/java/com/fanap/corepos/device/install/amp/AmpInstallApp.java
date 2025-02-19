package com.fanap.corepos.device.install.amp;

import com.fanap.corepos.device.install.FileUtil;
import com.fanap.corepos.device.install.InstallInterface;
import com.pos.device.sys.SystemManager;

import java.io.File;

public class AmpInstallApp implements InstallInterface {

    public boolean installApk(String apkAddress) {
        FileUtil.copyToCacheDir(apkAddress);
        String apkName = new File(apkAddress).getName();
        int result = SystemManager.installApp("/cache/apkfolder/"+apkName, 2);
        return result == 0;
    }

}
