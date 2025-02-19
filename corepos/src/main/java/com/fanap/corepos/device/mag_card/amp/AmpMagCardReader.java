package com.fanap.corepos.device.mag_card.amp;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.fanap.corepos.device.mag_card.MagCardInterface;
import com.pos.device.SDKException;
import com.pos.device.magcard.MagCardCallback;
import com.pos.device.magcard.MagCardReader;
import com.pos.device.magcard.MagneticCard;
import com.pos.device.ped.Ped;

public class AmpMagCardReader implements MagCardInterface {

    public static final String TAG = "AmpMagCardReader";
    private MagCardReader magCardReader;
    private Ped ped;

    Context context;
    private MutableLiveData<String> cardTrack2LiveData;

    public AmpMagCardReader(Context context) {
        magCardReader = MagCardReader.getInstance();
        this.context = context;
        Log.d(TAG, "AmpMagCardReader constractor");
    }

    void startMagCard() {
        Log.d(TAG, "call read method");
        if (magCardReader != null) {
            try {
                //int timeout=Integer.valueOf((((EditText) getView().findViewById(R.id.etTimeout)).getText().toString()));
                int timeout = 10;
                magCardReader.startSearchCard(timeout * 1000, magCardReaderCallback);
                Log.d(TAG, "search MAG CARD READER STARTED");
            } catch (SDKException e) {
                Log.d(TAG, "magCardReader.startSearchCard SDKException " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public void read() {
        startMagCard();
    }

    @Override
    public LiveData<String> getCardTrack2LiveData() {
        cardTrack2LiveData = new MutableLiveData<>();
        return cardTrack2LiveData;
    }

    @Override
    public void closeCardReader() {
        //cardTrack2LiveData = null;
        if (magCardReader != null) {
            try {
                magCardReader.stopSearchCard();
                Log.d(TAG, "search MAG CARD READER STOPPED");
            } catch (SDKException e) {
                Log.d(TAG, "magCardReader.stopSearchCard SDKException " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private MagCardCallback magCardReaderCallback=new MagCardCallback() {

        @Override
        public void onSearchResult(int i, MagneticCard magneticCard) {
            switch (i){
                case MagCardCallback.SUCCESS:
                    Log.d(TAG, "CARD READ SUCCESS");
                    String dataTrack2;
                    if (magneticCard!=null){
                        if(magneticCard.getTrackInfos(MagneticCard.TRACK_2).getState()==0) {
                            dataTrack2 = magneticCard.getTrackInfos(MagneticCard.TRACK_2).getData();
                            if (dataTrack2 != null)
                                cardTrack2LiveData.postValue(dataTrack2);
                            else
                                cardTrack2LiveData.postValue("");
                        }else
                            cardTrack2LiveData.postValue("");

                    }
                    closeCardReader();
                    break;
                case MagCardCallback.TIMEOUT_ERROR:
                    Log.d(TAG, "CARD READER TIMEOUT");
                    closeCardReader();
                    startMagCard();
                    break;
                case MagCardCallback.UNKNOWN_ERROR:
                    Log.d(TAG, "CARD READ UNKNOWN_ERROR");
                    closeCardReader();
                    startMagCard();
                    break;
                case MagCardCallback.USER_CANCEL:
                    //closeCardReader();
                    Log.d(TAG, "CARD READ USER_CANCEL");
                   // cardTrack2LiveData.postValue("");
                    break;
            }
        }
    };
}
