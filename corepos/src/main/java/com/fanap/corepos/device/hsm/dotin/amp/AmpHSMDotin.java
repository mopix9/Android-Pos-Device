package com.fanap.corepos.device.hsm.dotin.amp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.fanap.corepos.device.hsm.HSMInterface;
import com.fanap.corepos.device.hsm.util.HSMUtils;
import com.fanap.corepos.device.hsm.sina.amp.other.PinInfo;
import com.fanap.corepos.device.hsm.sina.amp.other.PinpadListener;
import com.fanap.corepos.device.hsm.sina.amp.other.PinpadManager;
import com.fanap.corepos.device.hsm.util.BCDHelper;
import com.fanap.corepos.utils.IsoUtil;
import com.pos.device.ped.KeySystem;
import com.pos.device.ped.KeyType;
import com.pos.device.ped.MACMode;
import com.pos.device.ped.Ped;
import com.pos.device.ped.PinBlockFormat;

import java.util.concurrent.CountDownLatch;

public class AmpHSMDotin implements HSMInterface, AmpPinActivity.PinResult {
    private Thread thread = null;
    private String expectPinLenList = "4";
    private String cardNumber = "0000000000000000";
    private int nRet = -1;
    private Ped pinpadProvider = null;
    private int mastKeyIndex = 0;
    private int mastKeyIndex2 = 4;
    private int pinKeyIndex = 1;
    private int macKeyIndex = 2;
    private int dataKeyId = 3;
    String TAG = "AmpHSMSina";
    private static AmpHSMDotin ampHSM;

    private MutableLiveData<String> passwordMutableLiveData = new MutableLiveData<>();

    public AmpHSMDotin() {
        pinpadProvider = Ped.getInstance();
        ampHSM = this;
    }

    public boolean loadMasterKey(String masterKey) {
        nRet = pinpadProvider.injectKey(KeySystem.MS_DES, KeyType.KEY_TYPE_MASTK, mastKeyIndex, HSMUtils.hexStringToByte(masterKey));
        nRet = pinpadProvider.injectKey(KeySystem.FIXED_DES, KeyType.KEY_TYPE_FIXEAK, mastKeyIndex2, HSMUtils.hexStringToByte(masterKey));
        if (nRet == 0) {
            Log.d(TAG, "Write Master Key ok");
            return true;
        } else {
            Log.d(TAG, "Write Master key fail nRet:" + nRet);
            return false;
        }
    }

    public boolean loadPinKey(String pinKey) {
        byte[] IV = new byte[8];
        byte[] plainPinKey = pinpadProvider.desDencryptUnified(KeySystem.FIXED_DES, KeyType.KEY_TYPE_FIXEAK, mastKeyIndex2, Ped.TDEA_DECRYPT, IV, HSMUtils.hexStringToByte(pinKey));
        loadDataKey(IsoUtil.bytesToHex(plainPinKey));

        nRet = pinpadProvider.writeKey(KeySystem.MS_DES, KeyType.KEY_TYPE_PINK, mastKeyIndex, pinKeyIndex, Ped.KEY_VERIFY_NONE, HSMUtils.hexStringToByte(pinKey));
        if (nRet == 0) {
            Log.d(TAG, "Write PIN key ok");
            return true;
        } else {
            Log.d(TAG, "Write PIN key fail nRet:" + nRet);
            return true;
        }
    }

    public boolean loadMacKey(String macKey) {

        nRet = pinpadProvider.writeKey(KeySystem.MS_DES, KeyType.KEY_TYPE_MACK, mastKeyIndex, macKeyIndex, Ped.KEY_VERIFY_NONE, BCDHelper.StrToBCD(macKey));
       // nRet = pinpadProvider.injectKey(KeySystem.MS_DES, KeyType.KEY_TYPE_MACK, macKeyIndex, BCDHelper.StrToBCD(macKey));
        if (nRet == 0) {
            Log.d(TAG, "Write MAC key ok");
            return true;
        } else {
            Log.d(TAG, "Write MAC key fail nRet:" + nRet);
            return false;
        }
    }

    @Override
    public boolean loadDataKey(String dataKey) {
        nRet = pinpadProvider.injectKey(KeySystem.FIXED_DES, KeyType.KEY_TYPE_FIXEAK, dataKeyId, IsoUtil.hexStringToByteArray(dataKey));
        if (nRet == 0) {
            Log.d(TAG, "Write DES key ok");
            return true;
        } else {
            Log.d(TAG, "Write DES key fail nRet:" + nRet);
            return false;
        }

    }

    @Override
    public byte[] calcMac(byte[] macData) {
        return pinpadProvider.getMac(KeySystem.MS_DES, macKeyIndex, MACMode.MAC_MODE_1, convertToEightMod(macData));
        /*if (mac != null)
            return BCDHelper.bcdToString(mac, 0, mac.length);
        else
            return null;*/
    }

    private byte[] convertToEightMod(byte[] bytes) {
        byte[] finalBytes;
        int eightMod = bytes.length % 8;
        if (eightMod == 0)
            return bytes;
        else {
            int size = 0;
            for (int i = eightMod; i < 8; i++)
                size++;
            finalBytes = new byte[(bytes.length + size)];
            for (int i = 0; i < bytes.length; i++) {
                finalBytes[i] = bytes[i];
            }
            for (int i = bytes.length; i < finalBytes.length; i++) {
                finalBytes[i] = 0x0;
            }
        }
        return finalBytes;
    }

    @Override
    public String decryptionData(String encryptedData) {

        byte[] IV = new byte[8];
      //  byte[] data = {(byte)0x55,(byte)0x6C,(byte)0xE9,(byte)0x07,(byte)0x86,(byte)0xAF,(byte)0x37,(byte)0x8B,(byte)0x2A,(byte)0xE2,(byte)0xCF,(byte)0x97,(byte)0x15,(byte)0xEB,(byte)0x73,(byte)0xE7};
        byte[] retData = pinpadProvider.desDencryptUnified(KeySystem.FIXED_DES, KeyType.KEY_TYPE_FIXEAK, dataKeyId, Ped.TDEA_DECRYPT | Ped.TDEA_MODE_ECB, IV, HSMUtils.hexStringToByte(encryptedData));

            if (retData != null) {
                Log.v(TAG, "Data Decrypted： " + BCDHelper.bcdToString(retData, 0, retData.length));
                new String(IsoUtil.hexStringToByteArray(HSMUtils.bytesToHex(retData)));
                return HSMUtils.bytesToHex(retData);
            } else {
                Log.v(TAG, "Decrypte fail");
            }

        return null;
    }

    @Override
    public String encryptionData(String data) {
        /*try {
            byte[] reData = pinpadProvider.calcDes(dataKeyId, PinpadConstant.TDES_TYPE_ENCRYPT, MyApplication.utils.ascToBcd(data));
            if (reData != null) {
                Log.v(TAG, "DES Encrypted ： " + MyApplication.utils.bcdToAsc(reData, reData.length));
                return MyApplication.utils.bcdToAsc(reData, reData.length);
            } else {
                Log.v(TAG, "calcDes fail");
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }*/
        return null;
    }

    @Override
    public void inputPin(String cardNumber, Context context) {
        Intent intent = new Intent(context,AmpPinActivity.class);
        intent.putExtra("CARD",cardNumber);
        intent.putExtra("LENGTH","4");
        intent.putExtra("TIMEOUT",30000);
        //intent.putExtra("INTERFACE",this);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static AmpPinActivity.PinResult provideListener(){
        return ampHSM;
    }


    @Override
    public void onPinResult(String pinBlock) {
        Log.d("PINNNNNN",pinBlock);
        passwordMutableLiveData.postValue(pinBlock);
    }

    public static String bytesToHex(byte[] bytes) {
        final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static String bytesToHex2(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X ", b));
        }
        return sb.toString();
    }

    public static final String bytesToHexString(byte[] bArray) {
        StringBuffer sb = new StringBuffer(bArray.length);
        int j = 0;

        for (int i = 0; i < bArray.length; ++i) {
            String sTemp = Integer.toHexString(255 & bArray[i]);
            if (sTemp.length() < 2) {
                sb.append(0);
            }

            sb.append(sTemp.toUpperCase());
            ++j;
        }

        return sb.toString();
    }

    @Override
    public MutableLiveData<String> getMutablePassword() {
        passwordMutableLiveData = new MutableLiveData<>();
        return passwordMutableLiveData;
    }


    private static PinInfo getPinpadOnlinePin(int keyIndex, int timeout, String amount, String cardNo) {
        Log.d("getPinpadOnlinePin", "[START]");
        final CountDownLatch mLatch = new CountDownLatch(1);
        final PinInfo pinInfo = new PinInfo();

        PinpadManager pinpadManager = PinpadManager.getInstance();

        // Key Management Type
        PinpadManager.keySystem = KeySystem.MS_DES;
        PinpadManager.keyIndex = keyIndex;

        Log.d("Online PIN", "keySystem=" + PinpadManager.keySystem);
        Log.d("Online PIN", "keyIndex=" + PinpadManager.keyIndex);

        // Pinblock Format
        PinpadManager.pinBlockFormat = PinBlockFormat.PIN_BLOCK_FORMAT_0;
        Log.d("Online PIN", "pinFormat=" + PinpadManager.pinBlockFormat);

        // Pin Length
        PinpadManager.pinLength = "4";
        Log.d("Online PIN", "pinLength=" + PinpadManager.pinLength);

        pinpadManager.getPin(timeout, amount, cardNo, new PinpadListener() {
            @Override
            public void callback(PinInfo info) {
                pinInfo.setResultFlag(info.isResultFlag());
                pinInfo.setErrno(info.getErrno());
                pinInfo.setNoPin(info.isNoPin());
                pinInfo.setPinblock(info.getPinblock());
               // Log.d("PinBlock", bytesToHex(info.getPinblock()));
                mLatch.countDown();

            }
        });
        try {
            mLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("getPinpadOnlinePin", "[END]");
        return pinInfo;
    }
}
