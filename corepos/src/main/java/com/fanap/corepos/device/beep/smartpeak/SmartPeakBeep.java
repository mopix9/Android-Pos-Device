package com.fanap.corepos.device.beep.smartpeak;

import com.fanap.corepos.device.beep.BeepInterface;
import com.fanap.corepos.device.beep.BeepType;
import com.pos.sdk.accessory.PosAccessoryManager;

public class SmartPeakBeep implements BeepInterface {

    @Override
    public void beep(BeepType beepType) {

        switch (beepType){
            case BEEP_TYPE_SUCCESS:
                PosAccessoryManager.getDefault().setBeep(true, 700, 100);
                break;
            case BEEP_TYPE_PROMPT:
                PosAccessoryManager.getDefault().setBeep(true, 900, 250);
                break;
            case BEEP_TYPE_FAULT:
            case BEEP_TYPE_ERROR:
                PosAccessoryManager.getDefault().setBeep(true, 900, 450);
                break;
        }
    }
}
