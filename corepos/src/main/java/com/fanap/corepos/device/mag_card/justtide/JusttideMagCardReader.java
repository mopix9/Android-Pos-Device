package com.fanap.corepos.device.mag_card.justtide;

import android.os.RemoteException;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.fanap.corepos.device.DeviceConfig;
import com.fanap.corepos.device.mag_card.MagCardInterface;
import com.justtide.service.dev.aidl.card.MSRCardInfo;
import com.justtide.service.dev.aidl.card.MSRCardReaderProvider;

public class JusttideMagCardReader implements MagCardInterface {
    int nRet = -1;
    private boolean bDetect = false;
    private MSRCardInfo msCardInfo;
    private MSRCardReaderProvider msrCardReaderProvider = null;
    private MutableLiveData<String> cardTrack2LiveData = new MutableLiveData<>();


    public JusttideMagCardReader() {
        try {
            msrCardReaderProvider = DeviceConfig.deviceProvider.getMSRCardReaderProvider();
            nRet = msrCardReaderProvider.open();

        } catch (RemoteException e) {
            Log.d("justtideMagCardReader", e.getMessage());
        }
    }

//    @Override
//    public void run() {
//        super.run();
//
//        cardReader();
//    }

    private void cardReader() {

//        try {
//            Log.d("justtideMagCardReader", "start to detect");
//            msrCardReaderProvider.readCard(CardReaderConstant.CARD_SWIPED, cardReaderListener, 35);
//
//        } catch (RemoteException e) {
//            Log.d("justtideMagCardReader", e.getMessage());
//        }
        Log.d("justtideMagCardReader", "while card detect ....");

        while (true) {
            try {
                bDetect = msrCardReaderProvider.detect();
                if (bDetect) {
                    msCardInfo = msrCardReaderProvider.read();
                    if (msCardInfo != null && !msCardInfo.getTrack2().equals("")) {
                        //SwipeFragment.TRACK2.postValue(msCardInfo.getTrack2());
                        cardTrack2LiveData.postValue(msCardInfo.getTrack2());
                        Log.d("justtideMagCardReader", msCardInfo.getTrack2());

                        break;
                    } else {
                        //SwipeFragment.TRACK2.postValue("");
                        cardTrack2LiveData.postValue("");
                        Log.d("justtideMagCardReader", "track2 null");
                    }
                }
            } catch (RemoteException e) {
                e.printStackTrace();
                Log.d("justtideMagCardReader", "CardReader Error :" + e.getMessage());
            }
        }

        /*
        try {
            nRet = msrCardReaderProvider.close();
        } catch (RemoteException e) {
            e.printStackTrace();
        }*/
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
            msrCardReaderProvider.close();
        }
        catch (Exception e){

        }
    }

//    CardReaderListener cardReaderListener = new CardReaderListener.Stub() {
//
//        @Override
//        public void onReadCardSucc(CardInfo info) throws RemoteException {
//            cardTrack2LiveData.postValue(info.getTrack2());
//            Log.d("justtideMagCardReader", info.getTrack2());
//        }
//
//        @Override
//        public void onReadCardErr(int errCode) throws RemoteException {
//            Log.d("justtideMagCardReader", "error : "+errCode);
//            cardReader();
//        }
//    };

    private void stopThread() {
        try {
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            Log.d("JusttideMagCardReader", e.getMessage());
        }
    }

}
