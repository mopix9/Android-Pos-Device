package com.fanap.corepos.device.date.justtide;

import android.os.RemoteException;

import com.fanap.corepos.device.DeviceConfig;
import com.fanap.corepos.device.date.DateTimeInterface;
import com.fanap.corepos.device.date.DateTimeUtils;
import com.justtider.justtideserver.DeviceDateProvider;

import java.util.List;

public class JusttideDateTime implements DateTimeInterface {
    private DeviceDateProvider dateProvider = null;
    String TAG = "justtideDateTime";

    public JusttideDateTime(){
        dateProvider = DeviceConfig.deviceDateProvider;
    }

    @Override
    public void setDateTime(int stamp) {
        try {
            List<Integer> time = DateTimeUtils.getFullTime(stamp);
            dateProvider.setSystemDataAndTime(time.get(0), time.get(1), time.get(2), time.get(3), time.get(4), time.get(5));
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setDateTime(String stamp) {

    }
}
