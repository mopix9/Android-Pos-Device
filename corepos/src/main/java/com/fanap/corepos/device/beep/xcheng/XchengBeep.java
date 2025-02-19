/*
package com.fanap.corepos.device.beep.xcheng;

import com.fanap.corepos.device.beep.BeepInterface;
import com.fanap.corepos.device.beep.BeepType;
import com.pos.sdk.accessory.POIGeneralAPI;

public class XchengBeep implements BeepInterface {

    @Override
    public void beep(BeepType beepType) {

        switch (beepType){
            case BEEP_TYPE_SUCCESS:
                POIGeneralAPI.getDefault().setBeep(true, 700, 100);
                break;
            case BEEP_TYPE_PROMPT:
                POIGeneralAPI.getDefault().setBeep(true, 900, 250);
                break;
            case BEEP_TYPE_FAULT:
                POIGeneralAPI.getDefault().setBeep(true, 900, 450);
                break;
            case BEEP_TYPE_ERROR:
                POIGeneralAPI.getDefault().setBeep(true, 900, 450);
                break;
        }
    }
}
*/
