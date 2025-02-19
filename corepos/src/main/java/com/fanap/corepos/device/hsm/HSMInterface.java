package com.fanap.corepos.device.hsm;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

public interface HSMInterface {
    public boolean loadMasterKey(String masterKey);
    public boolean loadPinKey(String pinKey);
    public boolean loadMacKey(String macKey);
//    public Boolean loadTransKey(String macKey);
    public boolean loadDataKey(String dataKey);
    public byte[] calcMac(byte[] bytes);
    public void inputPin(String cardNumber, Context context);  //init pinblock for enter pin
    public String decryptionData(String encryptedData);
    public String encryptionData(String data);

    public MutableLiveData<String> getMutablePassword(); //get encrypted pin from pinblock

}
