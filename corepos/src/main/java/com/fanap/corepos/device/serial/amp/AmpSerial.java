package com.fanap.corepos.device.serial.amp;

import android.os.Build;

import com.fanap.corepos.device.serial.SerialInterface;
import com.pos.device.config.DevConfig;

public class AmpSerial implements SerialInterface {

    @Override
    public String getSerial() {
       // return Build.SERIAL;
        return DevConfig.getSN();
    }
}
