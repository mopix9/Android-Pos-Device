/*
package com.fanap.corepos.device.mag_card.smartpeak;


import android.content.Context;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.fanap.corepos.device.mag_card.MagCardInterface;
import com.pos.sdk.cardreader.PosCardReaderManager;
import com.pos.sdk.cardreader.PosMagCardReader;
import com.pos.sdk.utils.PosUtils;

public class SmartPeakMagCard implements MagCardInterface {
    PosMagCardReader cardReader = null;
    int ret = -1;
    Context context;
    private static MutableLiveData<String> cardTrack2LiveData = new MutableLiveData<>();

    public SmartPeakMagCard(Context context) {
        cardTrack2LiveData = null;
        cardTrack2LiveData = new MutableLiveData<>();
        cardReader = PosCardReaderManager.getDefault(context).getMagCardReader();
        this.context = context;
        Log.d("cardReader", "smartMagCardReader constractor");
    }

    private void cardReader() {

        ret = cardReader.open();
        Log.d("cardReader", "open:: " + (ret == 0 ? "ok" : "fail"));

        if (ret != 0) {
            cardReader.close();
            ret = cardReader.open();
            Log.d("cardReader", "second try open:: " + (ret == 0 ? "ok" : "fail"));
        }

        int counter = 1;
        boolean detected = false;
        while (counter++ < counter +1) {
            Log.d("while card", String.valueOf(counter));

            if (cardReader.detect() == 0) {
                detected = true;
                break;
            }
            PosUtils.delayms(50);
        }
        if (detected) {
            byte[] track2 = null;
            track2 = cardReader.getTraceData(2);
            if (track2 != null) {
                cardTrack2LiveData.postValue(new String(track2));
                Log.d("cardReader", "detect::ok" + new String(track2));

            } else {
                cardTrack2LiveData.postValue("");
                Log.d("cardReader", "stripDataBytes null");

                ret = cardReader.close();
                Log.d("cardReader", "close:: " + (ret == 0 ? "ok" : "fail"));
            }
        }
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
//            cardTrack2LiveData = new MutableLiveData<>();
//            cardTrack2LiveData = null;
            ret = cardReader.close();
            Log.d("cardReader", "closeee:: " + (ret == 0 ? "ok" : "fail"));
        } catch (Exception ignored) {


        }
    }
}
*/

