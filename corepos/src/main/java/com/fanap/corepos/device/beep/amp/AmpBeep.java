package com.fanap.corepos.device.beep.amp;

import com.fanap.corepos.device.beep.BeepInterface;
import com.fanap.corepos.device.beep.BeepType;
import com.pos.device.SDKException;
import com.pos.device.beeper.Beeper;

public class AmpBeep implements BeepInterface {

    @Override
    public void beep(BeepType beepType) {

        switch (beepType){
            case BEEP_TYPE_SUCCESS:
                try {
                    Beeper.getInstance().beep(700,100);
                } catch (SDKException e) {
                    e.printStackTrace();
                }
                break;
            case BEEP_TYPE_PROMPT:
                try {
                    Beeper.getInstance().beep(900,250);
                } catch (SDKException e) {
                    e.printStackTrace();
                }
                break;
            case BEEP_TYPE_FAULT:
            case BEEP_TYPE_ERROR:
                try {
                    Beeper.getInstance().beep(900,450);
                } catch (SDKException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
