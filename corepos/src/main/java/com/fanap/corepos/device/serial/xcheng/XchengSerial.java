package com.fanap.corepos.device.serial.xcheng;

import android.app.AlarmManager;
import android.content.Context;
import android.util.Log;
import com.fanap.corepos.device.serial.SerialInterface;
import com.pos.sdk.accessory.POIGeneralAPI;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class XchengSerial implements SerialInterface {

    @Override
    public String getSerial() {
        return POIGeneralAPI.getDefault().getVersion(POIGeneralAPI.VERSION_TYPE_DSN);
    }
}
