/*

package com.fanap.corepos.device.mag_card.xcheng;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.fanap.corepos.device.mag_card.MagCardInterface;
import com.pos.sdk.cardreader.POICardManager;
import com.pos.sdk.cardreader.PosMagCardReader;
import com.pos.sdk.utils.PosUtils;

public class XchengMagCardReader implements MagCardInterface {

    PosMagCardReader cardReader = null;
    int ret = -1;
    Context context;
    private static MutableLiveData<String> cardTrack2LiveData;

    public XchengMagCardReader(Context context) {
        cardTrack2LiveData = null;
        cardTrack2LiveData = new MutableLiveData<>();
        cardReader = POICardManager.getDefault(context).getMagCardReader();
        this.context = context;
        Log.d("cardReader", "XchengMagCardReader constractor");
    }

    private void cardReader() {

        ret = cardReader.open();
        Log.d("cardReader", "open:: " + (ret == 0 ? "ok" : "fail"));

        if (ret != 0) {
            cardReader.close();
            ret = cardReader.open();
            Log.d("cardReader", "second try open:: " + (ret == 0 ? "ok" : "fail"));
        }

        //while (cnt++ < MAX_TRY_CNT)
        // if is detectTrue break -
        //if duration is finish break -
        //endMagnet is true => break
        int counter = 1;
        while (counter<200) {
            Log.d("while", String.valueOf(counter));

            if (cardReader.detect() == 0) {
                byte[] track2;
                track2 = cardReader.getTraceData(2);
                if (track2 != null) {
                    cardTrack2LiveData.postValue(new String(track2));
                    Log.d("cardReader", "detect::ok");
                    break;
                } else {
                    cardTrack2LiveData.postValue("");
                    Log.d("cardReader", "stripDataBytes null");
                }
            }
            PosUtils.delayms(50);
            counter++;
        }
        ret = cardReader.close();
        Log.d("cardReader", "close:: " + (ret == 0 ? "ok" : "fail"));
    }

    @Override
    public void read() {
        cardReader();
    }

    @Override
    public LiveData<String> getCardTrack2LiveData() {
        return cardTrack2LiveData;
    }

    @Override
    public void closeCardReader() {
        try {
//            cardTrack2LiveData = null;
            ret = cardReader.close();
            Log.d("cardReader", "close:: " + (ret == 0 ? "ok" : "fail"));
        } catch (Exception e) {

        }
    }
}

*/
