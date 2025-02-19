package com.fanap.corepos.device.hsm.sina.justtide;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.fanap.corepos.device.DeviceConfig;
import com.fanap.corepos.device.hsm.HSMInterface;
import com.fanap.corepos.utils.IsoUtil;
import com.justtide.service.dev.aidl.pinpad.PinpadConstant;
import com.justtide.service.dev.aidl.pinpad.PinpadProvider;

public class JusttideHSMSina implements HSMInterface {
    private Thread thread = null;
    private String strDesKey = "178F59F8578E0D3F178F59F8578E0D3F"; //encrypt des Key
    //private String expectPinLenList = "0,4,10,12";
    private String expectPinLenList = "4";
    private String cardNumber = "0000000000000000";
    private int nRet = -1 ;
    private PinpadProvider pinpadProvider = null;
    private int mastKeyId = 0;
    private int pinKeyId = 1;
    private int macKeyId = 2;
    private int desKeyId = 3;
    String TAG = "justtideHSM";

    private MutableLiveData<String> passwordMutableLiveData = new MutableLiveData<>();

    public JusttideHSMSina(){

        try {
            pinpadProvider = DeviceConfig.deviceProvider.getPinpadProvider();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public boolean loadMasterKey(String masterKey){
        try {
            nRet = pinpadProvider.importMasterKey(PinpadConstant.TDES_TYPE_NONE,
                    PinpadConstant.KEY_VERIFY_TYPE_NONE,
                    mastKeyId, mastKeyId, DeviceConfig.utils.ascToBcd(masterKey));
            if (nRet == 0) {
                Log.d(TAG, "Write Master Key ok");
                return true;
            }else {
                Log.d(TAG, "Write Master key fail nRet:" +nRet);
                return false;
            }
        } catch (RemoteException e4) {
            e4.printStackTrace();
            return false;
        }
    }

    public boolean loadPinKey(String pinKey){
        try {
            nRet = pinpadProvider.importPinKey(PinpadConstant.TDES_TYPE_DECRYPT,
                    PinpadConstant.KEY_VERIFY_TYPE_NONE,
                    mastKeyId, pinKeyId, DeviceConfig.utils.ascToBcd(pinKey));
            if (nRet == 0) {
                Log.d(TAG, "Write PIN key ok");
                return true;
            }else {
                Log.d(TAG, "Write PIN key fail nRet:" +nRet);
                return true;
            }
        } catch (RemoteException e3) {
            e3.printStackTrace();
            return false;
        }
    }

    public boolean loadMacKey(String macKey){
        try {
            nRet = pinpadProvider.importMacKey(PinpadConstant.TDES_TYPE_NONE,
                    PinpadConstant.KEY_VERIFY_TYPE_NONE,
                    mastKeyId, macKeyId, DeviceConfig.utils.ascToBcd(macKey));
            if (nRet == 0) {
                Log.d(TAG, "Write MAC key ok");
                return true;
            }else {
                Log.d(TAG, "Write MAC key fail nRet:" +nRet);
                return false;
            }
        } catch (RemoteException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public boolean loadDataKey(String dataKey){
        try {
            nRet = pinpadProvider.importDesKey(PinpadConstant.TDES_TYPE_NONE,
                    PinpadConstant.KEY_VERIFY_TYPE_NONE,
                    mastKeyId, desKeyId, IsoUtil.hexStringToByteArray(dataKey));
            if (nRet == 0) {
                Log.d(TAG, "Write DES key ok");
                return true;
            }else {
                Log.d(TAG, "Write DES key fail nRet:" +nRet);
                return false;
            }
        } catch (RemoteException e1) {
            e1.printStackTrace();
            return false;
        }
    }

    @Override
    public byte[] calcMac(byte[] macData){
        try {
            return pinpadProvider.calcMac(macKeyId, PinpadConstant.MAC_MODE_CBC,macData);
            /*if (mac != null) {
                return DeviceConfig.utils.bcdToAsc(mac, mac.length);
            }else {
                return null;
            }*/
        } catch (RemoteException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String decryptionData(String encryptedData) {
        try {
            byte[] reData = pinpadProvider.calcDes(desKeyId, PinpadConstant.TDES_TYPE_DECRYPT, DeviceConfig.utils.ascToBcd(encryptedData));
            if (reData != null) {
                Log.v(TAG,"DES Decrypted： "+ DeviceConfig.utils.bcdToAsc(reData, reData.length));
                return DeviceConfig.utils.bcdToAsc(reData, reData.length);
            }else {
                Log.v(TAG,"calcDes fail");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String encryptionData(String data) {
        try {
            byte[] reData = pinpadProvider.calcDes(desKeyId, PinpadConstant.TDES_TYPE_ENCRYPT, DeviceConfig.utils.ascToBcd(data));
            if (reData != null) {
                Log.v(TAG,"DES Encrypted ： "+ DeviceConfig.utils.bcdToAsc(reData, reData.length));
                return DeviceConfig.utils.bcdToAsc(reData, reData.length);
            }else {
                Log.v(TAG,"calcDes fail");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void inputPin(String cardNumber, Context context){
        this.cardNumber = cardNumber;
        if (thread  == null)
        {
            Log.v(TAG, "get pinblock");
            thread = new MyThread();
            thread.start();
        }
    }

    public String getRandom(){
        String strRandom = "";
        try {
            byte[] random = pinpadProvider.getRandom(8);
            if (random != null) {
                strRandom = DeviceConfig.utils.bcdToAsc(random,random.length);
                Log.v(TAG, "Random："+DeviceConfig.utils.bcdToAsc(random,random.length));
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        return strRandom;
    }

    private class MyThread extends Thread {

        public void run() {
            try {
                pinpadProvider.setInputPinTimeout(30);
                pinpadProvider.calcPinblock(pinKeyId, PinpadConstant.PIN_BLOCK_FORMAT_CUP,cardNumber.getBytes(), expectPinLenList.getBytes(), PinPadListener);
            } catch (RemoteException e) {
                e.printStackTrace();
                Log.d("justtideHSM", "Error : "+e.getMessage());
            }

            thread = null;
        }
    }

    @Override
    public MutableLiveData<String> getMutablePassword() {
        return passwordMutableLiveData;
    }

    com.justtide.service.dev.aidl.pinpad.PinPadListener PinPadListener = new com.justtide.service.dev.aidl.pinpad.PinPadListener.Stub() {

        @Override
        public void onInputResult(int retCode, byte[] data) throws RemoteException {
            String strShow;
            if (retCode == 0 ) {
                passwordMutableLiveData.postValue(DeviceConfig.utils.bcdToAsc(data, data.length));
            }else if (retCode == PinpadConstant.PINPAD_RET_CANCEL) {
                strShow = "Pinblock Cancel" ;
                passwordMutableLiveData.postValue("");
            }else if (retCode == PinpadConstant.PINPAD_RET_NOPIN) {
                passwordMutableLiveData.postValue("");
                strShow = "No PIN" ;
            }else if (retCode == PinpadConstant.PINPAD_RET_TIMEOUT) {
                passwordMutableLiveData.postValue("");
                strShow = "Pinblock TimeOut" ;
            }else {
                passwordMutableLiveData.postValue("");
                strShow = "Other error retCode:" + retCode;
            }
        }
    };

}
