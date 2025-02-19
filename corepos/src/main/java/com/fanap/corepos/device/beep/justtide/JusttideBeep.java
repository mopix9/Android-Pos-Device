package com.fanap.corepos.device.beep.justtide;

import android.os.RemoteException;

import com.fanap.corepos.device.DeviceConfig;
import com.fanap.corepos.device.beep.BeepInterface;
import com.fanap.corepos.device.beep.BeepType;
import com.justtide.service.dev.aidl.basic.BasicInfoProvider;

public class JusttideBeep implements BeepInterface {
    private Thread thread = null;
    private int beepType;
    private BasicInfoProvider basicInfoProvider = null;

    public JusttideBeep() {
        try {
            basicInfoProvider = DeviceConfig.deviceProvider.getBasicInfoProvider();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /*
    * param beepType : Utils.BEEP_TYPE_SUCCESS
     */
    @Override
    public void beep(BeepType beepType){
        this.beepType = beepType.getValue();
        if (thread == null)
        {
            thread = new BeepThread();
            thread.start();
        }
    }

    private class BeepThread extends Thread {

        public void run() {
            try {
                basicInfoProvider.beep(beepType);
                Thread.sleep(500);

            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            thread = null;
        }
    }
}
