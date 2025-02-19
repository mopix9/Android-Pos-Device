/*
package com.fanap.corepos.device.hsm.dotin.xcheng;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.basewin.utils.SpFunctions;
import com.fanap.corepos.device.hsm.HSMInterface;
import com.fanap.corepos.device.hsm.util.BCDHelper;
import com.fanap.corepos.device.hsm.util.SmartPeakHSM.GlobalData;
import com.pos.sdk.emvcore.POIEmvCoreManager;
import com.pos.sdk.security.POIHsmManage;
import com.pos.sdk.utils.PosByteArray;

import java.util.Arrays;

public class XchengHSMDotin implements HSMInterface {
    String TAG = "xchenghsm";
    private int area = 1;
    private int tmkindex = 1;
    private int macindex = 2;
    private int pinindex = 3;
   // private int tdkindex = 0x02;
    private int tdkindex = 0x04 ;
  //  private int tdkindex_e = 0x11;
  //  private int tdkindex_d = 0x10;

    private SpFunctions mSpFunctions = new SpFunctions();
    Context context;

    private MutableLiveData<String> passwordMutableLiveData = new MutableLiveData<>();

    public XchengHSMDotin(Context context) {
        this.context = context;
    }

    public void loadProtectKey(String key) {
        byte[] tlk = BCDHelper.StrToBCD(key);
        try {
            int ret = mSpFunctions.PedWriteKey(0, 0, SpFunctions.PED_TLK, area,
                    0, tlk.length, tlk, 0, null);

            if (ret == 0) {
                Log.d(TAG,"load protect Key Success!area = " + area + " protect key = " + key);
                GlobalData.getInstance().setPinpadVersion(PinpadInterfaceVersion.PINPAD_INTERFACE_VERSION);
            } else {
                Log.d(TAG,"load protect Key error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean loadDataKey(String key) {
        try {
            byte[] tlk = BCDHelper.StrToBCD(key);
            int iRet = 1;
            iRet = mSpFunctions.PedWriteKey(SpFunctions.PED_TMK, tmkindex,
                        SpFunctions.PED_TDK, tdkindex, 0, tlk.length, tlk, 0, null);

            if (iRet == 0) {
                Log.d(TAG,"load td Key Success!");
                return true;
            } else {
                Log.d(TAG,"error " + iRet);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG,"loadTDKey error !");
            return false;
        }
    }

    @Override
    public boolean loadMasterKey(String masterKey) {
       // mSpFunctions.PedErase();

       // loadTDKey(masterKey);
        format();
        //  loadProtectKey("11111111111111111111111111111111");
        if (masterKey.length() == 16)
            masterKey += masterKey;

        try {
            int iRet = -1;
                byte[] tlk = BCDHelper.StrToBCD(masterKey);
                iRet = mSpFunctions.PedWriteKey(0, 0,
                        SpFunctions.PED_TMK, tmkindex, 0, tlk.length, tlk, 0, null);

            if (iRet == 0) {
                Log.d(TAG,"load Main Key Success!");
                GlobalData.getInstance().setTmkId(tmkindex);
                return true;
            } else {
                Log.d(TAG, "error main key " + iRet);
                return false;

            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG,"loadMainKey error !");
            return false;
        }
    }

    @Override
    public boolean loadMacKey(String macKey) {
        if (macKey.length() == 16)
            macKey += macKey;

        Log.d(TAG, macKey);
        try {
            if (TextUtils.isEmpty(macKey)) {
                Log.d(TAG,"mac key  is  null  ！");
                return false;
            }
            int iRet = 1;
                byte[] tlk = BCDHelper.StrToBCD(macKey);
                iRet = mSpFunctions.PedWriteKey(SpFunctions.PED_TMK, tmkindex,
                        SpFunctions.PED_TAK, macindex, 0, tlk.length, tlk, 0, null);

            if (iRet == 0) {
                Log.d(TAG,"load mac Key Success!");
                return true;
            } else {
                Log.d(TAG,"error mac key " + iRet);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG,"loadMacKey error !");
            return false;
        }
    }

    @Override
    public boolean loadPinKey(String pinKey) {
        if (pinKey.length() == 16)
            pinKey += pinKey;


        loadDataKey(pinKey);

        try {
            if (TextUtils.isEmpty(pinKey)) {
                Log.d(TAG,"pin key  is  null  ！");
                return false;
            }

            int iRet = 1;

                byte[] tlk = BCDHelper.StrToBCD(pinKey);
                iRet = mSpFunctions.PedWriteKey(SpFunctions.PED_TMK, tmkindex,
                        SpFunctions.PED_TPK, pinindex, 0, tlk.length, tlk, 0, null);

            if (iRet == 0) {
                Log.d(TAG,"load pin Key Success!");
                GlobalData.getInstance().setPinkeyFlag(true);
                GlobalData.getInstance().setPinId(pinindex);
                return true;
            } else {
                Log.d(TAG,"error pin key " + iRet);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d(TAG,"loadPinKey error:"+e.getMessage());
            return false;
        }
    }

    @Override
    public byte[] calcMac(byte[] macData){
        try {
            byte[] DataIn = macData;
            byte[] MacOut = new byte[128];

            int ret = -1;
            PosByteArray rspBuf = new PosByteArray(MacOut, MacOut.length);
            ret = POIHsmManage.getDefault().PedGetMac(macindex, 3, DataIn, rspBuf);
            if (ret == 0) {
Log.d("macKey", BCDHelper.bcdToString(rspBuf.buffer, 0, rspBuf.buffer.length));
                return BCDHelper.bcdToString(rspBuf.buffer, 0, rspBuf.buffer.length);

                return rspBuf.buffer;
            } else {
Log.d("macKey", String.valueOf(ret));

                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("macKey", String.valueOf(e.getMessage()));
            return null;
        }
    }

    @Override
    public void inputPin(String cardNumber, Context context) {
        Bundle bundle = new Bundle();
        bundle.putString(POIEmvCoreManager.EmvPinConstraints.PIN_CARD, cardNumber);
        bundle.putInt(POIEmvCoreManager.EmvPinConstraints.PIN_TYPE, POIEmvCoreManager.PIN_ONLINE_PIN);

        PinpadDialog dialog = new PinpadDialog(context, bundle, 3);
        dialog.setListener(new PinpadDialog.PWListener() {
            @Override
            public void onConfirm(int VerifyResult, byte[] pinblock, byte[] pinksn) {
                if (pinblock != null) {
                    passwordMutableLiveData.postValue(BCDHelper.bcdToString(pinblock, 0, pinblock.length));;
                } else
                    passwordMutableLiveData.postValue("");
            }

            @Override
            public void onCancel() {
                passwordMutableLiveData.postValue("");
                Log.d(TAG,"Password Cancel");
            }

            @Override
            public void onError(int VerifyResult, int PinTryCntOut) {
                passwordMutableLiveData.postValue("");
                Log.d(TAG,"Password Error " + VerifyResult);
            }

        });
        dialog.showDialog();
    }

    @Override
    public String encryptionData(String data) {
        try {
          //  data = BCDHelper.toHexString(data.getBytes());
            byte[] DataIn = encryptDataPadding(BCDHelper.StrToBCD(data));
            byte[] DataOut = new byte[128];
            PosByteArray posByteArray = new PosByteArray(DataOut, DataOut.length);

            POIHsmManage.getDefault().PedCalDes(tdkindex, 1, DataIn, posByteArray);

            return BCDHelper.bcdToString(posByteArray.buffer, 0, posByteArray.len);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String decryptionData(String encryptedData) {
        try {
            byte[] DataIn = encryptDataPadding(BCDHelper.StrToBCD(encryptedData));
            byte[] DataOut = new byte[128];
            PosByteArray posByteArray = new PosByteArray(DataOut, DataOut.length);
            POIHsmManage.getDefault().PedCalDes(tdkindex, 0, DataIn, posByteArray);
            return BCDHelper.bcdToString(posByteArray.buffer, 0, posByteArray.len);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("hsm_encryption_", e.getMessage());
            return null;
        }
    }

    private byte[] encryptDataPadding(byte[] originalData) {
        int originalLength = originalData.length;
        int dataLength = originalLength;
        while (dataLength % 8 != 0)
            dataLength++;

        byte[] dados = new byte[dataLength];
        Arrays.fill(dados, (byte) 0xFF);

        System.arraycopy(originalData, 0, dados, 0, originalLength);
        return dados;
    }


    private byte[] EncryptDataPadding(byte[] originalData) {
        int originalLength = originalData.length;
        int dataLength = originalLength;
        while (dataLength % 8 != 0)
            dataLength++;

        // Completa o padding com bytes 0xFF
        byte[] dados = new byte[dataLength];
        Arrays.fill(dados, (byte) 0xFF);

        System.arraycopy(originalData, 0, dados, 0, originalLength);
        return dados;
    }

    public void format() {
        try {
            mSpFunctions.PedErase();
            GlobalData.getInstance().setPinkeyFlag(false);
            Log.d(TAG, "format success!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public MutableLiveData<String> getMutablePassword() {
        return passwordMutableLiveData;
    }

}
*/
