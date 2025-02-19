package com.fanap.corepos.device.serial.smartpeak;

import com.fanap.corepos.device.serial.SerialInterface;
import com.pos.sdk.accessory.PosAccessoryManager;


public class SmartPeakSerial implements SerialInterface {

    @Override
    public String getSerial() {
//        return PosAccessoryManager.getDefault().getVersion(PosAccessoryManager.VERSION_TYPE_DSN);
        return "00001504P6000003690";//p600 serial

    }
}
