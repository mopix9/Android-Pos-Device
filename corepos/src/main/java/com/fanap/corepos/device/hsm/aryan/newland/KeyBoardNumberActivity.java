
package com.fanap.corepos.device.hsm.aryan.newland;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.TextView;

import com.fanap.corepos.R;
import com.newland.nsdk.core.api.common.ModuleType;
import com.newland.nsdk.core.api.common.exception.NSDKException;
import com.newland.nsdk.core.api.common.keymanager.KeyType;
import com.newland.nsdk.core.api.common.keymanager.KeyUsage;
import com.newland.nsdk.core.api.common.keymanager.SymmetricKey;
import com.newland.nsdk.core.api.common.pinentry.PINBlockMode;
import com.newland.nsdk.core.api.common.utils.ISOUtils;
import com.newland.nsdk.core.api.common.utils.LogUtils;
import com.newland.nsdk.core.api.internal.pinentry.PINEntry;
import com.newland.nsdk.core.api.internal.pinentry.PINEntryListener;
import com.newland.nsdk.core.api.internal.pinentry.PINEntryParameters;
import com.newland.nsdk.core.internal.NSDKModuleManagerImpl;

import java.io.Serializable;




import android.app.Activity;

/**
 * Password Keyboard Activity
 */

public class KeyBoardNumberActivity extends Activity {
    private static final String TAG = "KeyBoardNumberActivity";
    private static final int LAYOUT_MODE_NORMAL = 0;
    private static final int LAYOUT_MODE_RANDOM_NUM = 1;
    private int layoutMode = LAYOUT_MODE_RANDOM_NUM;
    private TextView txtPassword;
    private StringBuffer buffer;
    private int inputLen = 0;
    private PinKeyBoard pkb;
    private PINEntry pinInput;
    // keytype, 0-DES, 1-AES, 2-DUKPT
    private int keyType = 0;

    public interface PinResult extends Serializable {
        void onPinResult(String pinBlock);
    }

    private PinResult pinResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_pin_fragment);
        pinInput = (PINEntry) NSDKModuleManagerImpl.getInstance().getModule(ModuleType.PIN_ENTRY);
        keyType = getIntent().getIntExtra("keytype", 0);
        pinResult = NewLandHSMAryan.provideListener();
        init();


    }




    private void init() {
        txtPassword = findViewById(R.id.txt_password);
        pkb = findViewById(R.id.n900pinkeyboard);
        final String accNo = getIntent().getStringExtra("CARD");
        pkb.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
            private boolean first;//  To prevent it from entering the onPreDraw() all the time.

            @Override
            public boolean onPreDraw() {
                if (!first) {
                    first = true;
                    boolean bool = getRandomKeyBoardNumber();
                    if (!bool) {
                        finish();
                        return first;
                    }
                    SymmetricKey desKey = new SymmetricKey();

                    final PINEntryParameters params = new PINEntryParameters();
                    params.setPINBlockMode(PINBlockMode.ISO9564_0);

                    if (keyType == 1) {
                        desKey.setKeyID(AppConfig.Keys.MKSK_AES_INDEX_WK_PIN);
                        desKey.setKeyType(KeyType.AES);
                        desKey.setKeyUsage(KeyUsage.PIN);
                        params.setPINBlockMode(PINBlockMode.ISO9564_4);
                    } else if (keyType == 2){
                        desKey.setKeyID(AppConfig.Keys.DUKPT_DES_INDEX);
                        desKey.setKeyType(KeyType.DES);
                        desKey.setKeyUsage(KeyUsage.DUKPT);
                    } else {
                        desKey.setKeyID(AppConfig.Keys.MKSK_DES_INDEX_WK_PIN);
                        desKey.setKeyType(KeyType.DES);
                        desKey.setKeyUsage(KeyUsage.PIN);
                    }

                    params.setMinPINLen(4);
                    params.setMaxPINLen(4);
                    params.setPINLengthRange(new byte[]{0x04});
                    int ret = 0;
                    try {
                        pinInput.startOnlinePINEntry(desKey, accNo,60, params, mPInInputListener);
                    } catch (NSDKException e) {
                        // todo Handle the exception
                        e.printStackTrace();
                    }
                }

                return first;
            }
        });

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Bundle finishResult = (Bundle) msg.obj;
                    int pinLen = finishResult.getInt("pinLen");
                    byte[] pinBlock = finishResult.getByteArray("pinBlock");
                    Log.d("pinBlockByte" , pinBlock.toString());
                    byte[] ksn = finishResult.getByteArray("ksn");
                    String pinBlockStr = "PIN Block=" + (pinBlock.length == 0 ? "null" : ISOUtils.hexString(pinBlock));
                    Log.d("pinBlockStr" , pinBlockStr);
                    String ksnStr = "KSN=" + (ksn.length == 0 ? "null" : ISOUtils.hexString(ksn));
                    String displayMessage = String.format("%s,%s", pinBlockStr, ksnStr);
                    //Toast.makeText(KeyBoardNumberActivity.this, displayMessage, Toast.LENGTH_LONG).show();
                    pinResult.onPinResult(ISOUtils.hexString(pinBlock));

                    break;
                case 2: // inputting
                    int len = (Integer) msg.obj;
                    buffer = new StringBuffer();
                    for (int i = 0; i < len; i++) {
                        buffer.append(" * ");
                    }
                    txtPassword.setText(buffer.toString());
                    break;

                default:
                    break;
            }
        }
    };

    private boolean getRandomKeyBoardNumber() {
        try {
            byte[] initCoordinate = pkb.getCoordinate();
            byte[] keySeq = ISOUtils.hex2byte("3132331b3435360a3738392e301c0d");

            byte[] numbtn = new byte[80];
            byte[] funbtn = new byte[36];
            setKeyCoordinate(initCoordinate, keySeq, numbtn, funbtn);
            byte[] outSeq = pinInput.initKeyLayout(numbtn, funbtn,true);
            byte[] targetKeySeq = keySeq;
            if (layoutMode == LAYOUT_MODE_RANDOM_NUM) {
                targetKeySeq = getKeySeq(outSeq);
            }
            pkb.loadRandomKeyboardfinished(targetKeySeq);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private int mCurrKeyLen = 0;

    private void setKeyLen(int len) {
        Message msg = mHandler.obtainMessage(2);
        msg.obj = len;
        msg.sendToTarget();
    }

    private PINEntryListener mPInInputListener = new PINEntryListener() {
        @Override
        public void onFinish(int pinLen, byte[] pinBlock, byte[] ksn) {
            LogUtils.d(TAG, "onFinish: pinLen=" + pinLen);
            LogUtils.d(TAG, "onFinish: pinblock=" + (pinBlock != null ? ISOUtils.hexString(pinBlock) : "null"));
            LogUtils.d(TAG, "onFinish: ksn=" + (ksn != null ? ISOUtils.hexString(ksn) : "null"));
            Message msg = mHandler.obtainMessage(1);
            Bundle finishResult = new Bundle();
            finishResult.putInt("pinLen", pinLen);
            finishResult.putByteArray("pinBlock", pinBlock == null ? new byte[0] : pinBlock);

            Log.d("pinresult" ,(ISOUtils.hexString(pinBlock)));


            finishResult.putByteArray("ksn", ksn == null ? new byte[0] : ksn);
            msg.obj = finishResult;
            msg.sendToTarget();
            finish();
        }

        @Override
        public void onTimeout() {
            LogUtils.d(TAG, "onTimeOut: ");
            pinResult.onPinResult("");
            finish();
        }

        @Override
        public void onKeyPress() {
            LogUtils.d(TAG, "onKeyPress: ");
            mCurrKeyLen = mCurrKeyLen + 1;
            setKeyLen(mCurrKeyLen);
        }

        @Override
        public void onCancel() {
            LogUtils.d(TAG, "onCancel: ");
            pinResult.onPinResult("");
            finish();
        }

        @Override
        public void onClear() {
            LogUtils.d(TAG, "onClear: ");
            mCurrKeyLen = 0;
            setKeyLen(mCurrKeyLen);
        }

        @Override
        public void onBackspace() {
            LogUtils.d(TAG, "onBackspace: ");
            mCurrKeyLen = mCurrKeyLen - 1;
            if (mCurrKeyLen < 0) {
                mCurrKeyLen = 0;
            }
            setKeyLen(mCurrKeyLen);
        }

        @Override
        public void onError(int errorCode, String message) {
            LogUtils.d(TAG, String.format("onError: %d, %s", errorCode, message));
            pinResult.onPinResult("");
            finish();
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private byte[] getKeySeq(byte[] outSeq) {
        byte[] keySeq = new byte[15];
        byte[] fiveKey = new byte[]{0x1b, 0x0a, 0x0d, 0x2e, 0x1c};
        int n2 = 0, n1 = 0;
        for (int i = 0; i < 15; i++) {
            if (i == 11 || i == 13) {
                if (i == 11) {
                    keySeq[i] = 0x2e;
                }
                if (i == 13) {
                    keySeq[i] = 0x1c;
                }
            } else if (i == 3 || i == 7 || i == 14) {
                keySeq[i] = fiveKey[n1++];
            } else {
                keySeq[i] = outSeq[n2++];
            }
        }
        return keySeq;
    }

    private void setKeyCoordinate(byte[] initCoordinate, byte[] keySeq, byte[] numBtn, byte[] funKey) {
        int n2 = 0;
        int offset = 0;
        int funOffset = 0, numBtnOffset = 0;
        byte[] x1 = new byte[2], y1 = new byte[2], x2 = new byte[2], y2 = new byte[2];
        byte[] fiveKey = new byte[]{0x1b, 0x0a, 0x0d, 0x2e, 0x1c};
        if (layoutMode == LAYOUT_MODE_NORMAL) {
            for (int i = 0; i < keySeq.length; i++) {
                System.arraycopy(initCoordinate, offset, x1, 0, 2);
                x1 = endianSwab16(x1);
                offset += 2;
                System.arraycopy(initCoordinate, offset, y1, 0, 2);
                y1 = endianSwab16(y1);
                offset += 2;
                System.arraycopy(initCoordinate, offset, x2, 0, 2);
                x2 = endianSwab16(x2);
                offset += 2;
                System.arraycopy(initCoordinate, offset, y2, 0, 2);
                y2 = endianSwab16(y2);
                offset += 2;

                if (keySeq[n2] >= '0' && keySeq[n2] <= '9') {
                    numBtnOffset = (keySeq[n2] - '0') * 8;
                    System.arraycopy(x1, 0, numBtn, numBtnOffset, 2);
                    numBtnOffset += 2;
                    System.arraycopy(y1, 0, numBtn, numBtnOffset, 2);
                    numBtnOffset += 2;
                    System.arraycopy(x2, 0, numBtn, numBtnOffset, 2);
                    numBtnOffset += 2;
                    System.arraycopy(y2, 0, numBtn, numBtnOffset, 2);
                    numBtnOffset += 2;
                }

                if (keySeq[n2] == 0x1B || keySeq[n2] == 0x0A || keySeq[n2] == 0x0D || keySeq[n2] == 0x9b || keySeq[n2] == 0x9c) {
                    byte[] temp = new byte[4];
                    System.arraycopy(keySeq, n2, temp, 0, 1);
                    System.arraycopy(temp, 0, funKey, funOffset, 4);
                    funOffset += 4;
                    System.arraycopy(x1, 0, funKey, funOffset, 2);
                    funOffset += 2;
                    System.arraycopy(y1, 0, funKey, funOffset, 2);
                    funOffset += 2;
                    System.arraycopy(x2, 0, funKey, funOffset, 2);
                    funOffset += 2;
                    System.arraycopy(y2, 0, funKey, funOffset, 2);
                    funOffset += 2;
                }
                n2++;
            }
        } else if (layoutMode == LAYOUT_MODE_RANDOM_NUM) {
            for (int i = 0; i < 15; i++) {
                System.arraycopy(initCoordinate, offset, x1, 0, 2);
                x1 = endianSwab16(x1);
                offset += 2;
                System.arraycopy(initCoordinate, offset, y1, 0, 2);
                y1 = endianSwab16(y1);
                offset += 2;
                System.arraycopy(initCoordinate, offset, x2, 0, 2);
                x2 = endianSwab16(x2);
                offset += 2;
                System.arraycopy(initCoordinate, offset, y2, 0, 2);
                y2 = endianSwab16(y2);
                offset += 2;
                if (i == 11 || i == 13) {
                    continue;
                } else if (i == 3 || i == 7 || i == 14) {
                    byte[] temp = new byte[4];
                    System.arraycopy(fiveKey, funOffset / 12, temp, 0, 1);
                    System.arraycopy(temp, 0, funKey, funOffset, 4);
                    funOffset += 4;
                    System.arraycopy(x1, 0, funKey, funOffset, 2);
                    funOffset += 2;
                    System.arraycopy(y1, 0, funKey, funOffset, 2);
                    funOffset += 2;
                    System.arraycopy(x2, 0, funKey, funOffset, 2);
                    funOffset += 2;
                    System.arraycopy(y2, 0, funKey, funOffset, 2);
                    funOffset += 2;
                } else {
                    System.arraycopy(x1, 0, numBtn, numBtnOffset, 2);
                    numBtnOffset += 2;
                    System.arraycopy(y1, 0, numBtn, numBtnOffset, 2);
                    numBtnOffset += 2;
                    System.arraycopy(x2, 0, numBtn, numBtnOffset, 2);
                    numBtnOffset += 2;
                    System.arraycopy(y2, 0, numBtn, numBtnOffset, 2);
                    numBtnOffset += 2;
                }
            }
        }

    }

    private byte[] endianSwab16(byte[] data) {
        try {
            byte[] temp = new byte[2];
            temp[0] = data[1];
            temp[1] = data[0];
            return temp;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
}

