package com.fanap.corepos.device.serial.justtide;

import com.fanap.corepos.device.DeviceConfig;
import com.fanap.corepos.device.serial.SerialInterface;
import com.justtide.service.dev.aidl.basic.BasicInfo;
import com.justtide.service.dev.aidl.basic.BasicInfoProvider;
import com.justtider.justtideserver.DeviceDateProvider;

import java.util.List;

public class JusttideSerial implements SerialInterface {
    String TAG = "justtideSerial";

    @Override
    public String getSerial() {
        BasicInfoProvider basicInfoProvider = null;
        String serial = "";
        try {
            basicInfoProvider = DeviceConfig.deviceProvider.getBasicInfoProvider();
            BasicInfo basicInfo = basicInfoProvider.getBasicInfo();
            serial = basicInfo.getSn();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serial;
    }
}
