package com.fanap.corepos.device.hsm.sayan.amp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fanap.corepos.R;
import com.fanap.corepos.device.hsm.sayan.amp.KeyBoardView;
import com.fanap.corepos.device.hsm.sayan.amp.other.AmpConstant;
import com.fanap.corepos.device.hsm.sayan.amp.other.ISOUtil;
import com.fanap.corepos.device.hsm.sayan.amp.other.StringUtil2;
import com.fanap.corepos.utils.IsoUtil;
import com.pos.device.SDKException;
import com.pos.device.ped.KeySystem;
import com.pos.device.ped.Ped;
import com.pos.device.ped.PinBlockCallback;
import com.pos.device.ped.PinBlockFormat;
import com.pos.device.ped.PinKeyInfoCallback;
import com.secure.api.PinKeyInfo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

public class AmpPinActivity extends AppCompatActivity {

    interface PinResult extends Serializable {
        void onPinResult(String pinBlock);
    }

    private WindowManager windowManager;
    private PinKeyInfo[] pinKeyInfos;
    private String TAG = "AmpPinActivity";
    private KeyBoardView keyBoardView;
    private ViewGroup parentView;
    private WindowManager.LayoutParams params;
    private CountDownTimer countDownTimer = null;
    private TextView leftTimeView;
    private TextView pinTextView;
    private static volatile boolean windowAttached = false;
    private PinResult pinResult;
    private String cardNumber,pinLength;
    private int timeout;

    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case AmpConstant.ADD_WINDOW:
                    if (!windowAttached) {
                        windowManager.addView(parentView, params);
                        windowAttached = true;
                    }
                    break;
                case AmpConstant.REMOVE_WINDOW:
                    if (countDownTimer != null) {
                        countDownTimer.cancel();
                        countDownTimer = null;
                    }
                    if (windowAttached) {
                        windowManager.removeViewImmediate(parentView);
                        windowAttached = false;
                    }
                    finish();
                    break;
                case AmpConstant.KEYMAP_DISPLAYED:
                    if (msg.arg1 == 0) {//键盘键值显示完成
                        keyBoardView.setKeyMap((PinKeyInfo[]) msg.obj);
                        final int pinEntryTimeout = timeout+2000;//msg.arg2 + 2000;
                        final int countDownInterval = 1000;
                        countDownTimer = new CountDownTimer(pinEntryTimeout, countDownInterval) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                long leftTime = millisUntilFinished / 1000 - 1;
                                if (leftTimeView != null)
                                    leftTimeView.setText(String.format(getResources().getString(R.string.remaining_seconds), leftTime));
                                if (leftTime == 0) {//确定或取消键和PIN输入超时
                                    this.cancel();
                                }
                            }

                            @Override
                            public void onFinish() {
                            }
                        };
                        countDownTimer.start();
                    } else {
                        mHandler.sendEmptyMessage(AmpConstant.REMOVE_WINDOW);
                    }
                    break;
                case AmpConstant.SYMBOL_DISPLAYED:
                    StringBuilder symbols = new StringBuilder();
                    for (int i = 0; i < msg.arg1; i++) {
                        symbols.append("*");
                    }
                    pinTextView.setText(symbols.toString());
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        cardNumber = getIntent().getExtras().getString("CARD");
        pinLength = getIntent().getExtras().getString("LENGTH");
        timeout = getIntent().getExtras().getInt("TIMEOUT");
        pinResult = AmpHSMSayan.provideListener();
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        showPinKeyBoard();
    }

    private void showPinKeyBoard() {
        params = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        }

        params.format = PixelFormat.RGBA_8888;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        params.gravity = Gravity.TOP | Gravity.CENTER;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
                | WindowManager.LayoutParams.FLAG_SECURE
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON;
        parentView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.pinkeyboardlayoutsayan, null);
        keyBoardView = parentView.findViewById(R.id.layout_keyboard_area);
        leftTimeView = parentView.findViewById(R.id.left_time_tips);
        pinTextView = parentView.findViewById(R.id.pwd_symbol);
        mHandler.sendEmptyMessage(AmpConstant.ADD_WINDOW);
        new Thread(new Runnable() {
            @Override
            public void run() {
                pinKeyInfos = keyBoardView.getKeyMap();
                try {
                    getPinWithCustom();
                } catch (SDKException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void getPinWithCustom() throws SDKException {
        PinBlockFormat format = PinBlockFormat.PIN_BLOCK_FORMAT_0;
        // copyApkFromAssets(getApplicationContext(), "sample.ogg");
        //writeFiletoSystem(getApplicationContext(), "/data/ums_share/sample.ogg");
        //Ped.getInstance().setPinKeyTones("/data/ums_share/sample.ogg", "/data/ums_share/sample.ogg");
        Ped.getInstance().setPinEntryChangeListener(new Ped.PinEntryChangeListener() {
            @Override
            public void onChange(int i) {
                //Log.d(TAG, "pin entry number:" + i);
                if (i == 6) {
                    Log.d(TAG, "-------------enterInputPin before---------------");
                    //Ped.getInstance().enterInputPin();
                    Log.d(TAG, "-------------enterInputPin after---------------");
                }
            }
        });

        cardNumber = cardNumber.substring(cardNumber.length() - 13, cardNumber.length() - 1);
        cardNumber = ISOUtil.padleft(cardNumber, cardNumber.length() + 4, '0');
        Ped.getInstance().setPinEntryTimeout(timeout/1000);
        Ped.getInstance().getPinBlock(KeySystem.MS_DES, 1, format, pinLength, cardNumber, pinKeyInfos, new PinBlockCallback() {
            @Override
            public void onPinBlock(int retCode, byte[] pinBlock) {
                try {
                    pinResult.onPinResult(StringUtil2.hexString(pinBlock));
                }catch (Exception e){
                    pinResult.onPinResult("");
                }
                Log.d(TAG, "======getPinBlock========> retCode=" + retCode + ";pinBlock=" + IsoUtil.bytesToHex(pinBlock));
                finish();
            }
        }, new PinKeyInfoCallback() {
            @Override
            public void onPinKeyInfo(int i, final PinKeyInfo[] pinKeyInfos, int timeout) {
                Log.d(TAG,"onPinKeyInfo");
                mHandler.sendMessage(mHandler.obtainMessage(AmpConstant.KEYMAP_DISPLAYED, 0, timeout, pinKeyInfos));
            }

            @Override
            public void onPinInput(int status, int inputCount, int leftTime) {
                Log.d(TAG, "onPinInput status = " + status + " inputCount = " + inputCount + " leftTime = " + leftTime);
                if (status == 3) {
                    runOnUiThread(() -> Toast.makeText(AmpPinActivity.this,"لطفا رمز معتبر وارد نمائید!", Toast.LENGTH_SHORT).show());
                }
                mHandler.sendMessage(mHandler.obtainMessage(AmpConstant.SYMBOL_DISPLAYED, inputCount, 0));
                if (status == AmpConstant.PIN_STATUS_CANCEL || status == AmpConstant.PIN_STATUS_READY || leftTime == 0) {
                    mHandler.sendEmptyMessage(AmpConstant.REMOVE_WINDOW);
                }
            }
        });
    }

    public static boolean writeFiletoSystem(Context context, String outputFile)  {
        File dir = new File(outputFile);
        if (!dir.exists()) {
            // create file
            try {
                dir.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        else
        {
            Log.d("writeFiletoSystem", "file exist");
        }
        try {
            Runtime.getRuntime().exec("chmod 777 "+outputFile);//
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        try {
            InputStream is = context.getAssets().open("sample.ogg");
            FileOutputStream fos = new FileOutputStream(outputFile);

            byte[] temp = new byte[1024];
            int i;
            while ((i = is.read(temp)) > 0) {
                fos.write(temp, 0, i);
            }
            fos.close();
            is.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

//        try {
//            FileOutputStream outputStream = new FileOutputStream(outputFile);
//            int length =data.length;
//            outputStream.write(data, 0, length);
//            outputStream.flush();
//            outputStream.close();
//            return true;
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
    }

    public static void copyApkFromAssets(Context context, String fileName)
    {
        String ApplDir = context.getFilesDir().toString();

        File BaseDir = new File(ApplDir + "/AMPBaseApp");
        File AgnosDir = new File(ApplDir + "/AGNOS");
        File ImagesDir = new File(ApplDir + "/AMPBaseApp/images");

        boolean bBaseDirCreated = false;
        if(!BaseDir.exists())
            bBaseDirCreated = BaseDir.mkdirs();
        boolean bDir1Created = AgnosDir.mkdirs();
        boolean bImagesCreated = false;
        if(!ImagesDir.exists())
            bImagesCreated = ImagesDir.mkdirs();

        String[] cmdAMPBaseApp  = {"chmod", "-R", "770", ApplDir + "/AMPBaseApp/"};
        String[] cmdAgnos       = {"chmod", "-R", "770", ApplDir + "/AGNOS/"};
        ProcessBuilder bldrAMPBaseApp = new ProcessBuilder(cmdAMPBaseApp);
        ProcessBuilder bldrAgnos = new ProcessBuilder(cmdAgnos);
        try {
            bldrAMPBaseApp.start();
            bldrAgnos.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("Create BaseApp Dir", BaseDir.toString() + " = " + bBaseDirCreated);
        Log.d("Create Agnos Dir", AgnosDir.toString() + " = " + bDir1Created);
        Log.d("Create Images Dir", AgnosDir.toString() + " = " + bImagesCreated);

        try {
            InputStream is = context.getAssets().open("sample.ogg");

            String targetPath;
            targetPath =  "/data/ums_share/sample.ogg";

            File file = new File(targetPath);

            // if(file.exists() && (!Overwrite))
            //     return;

            FileOutputStream fos = new FileOutputStream(file);

            byte[] temp = new byte[1024];
            int i;
            while ((i = is.read(temp)) > 0) {
                fos.write(temp, 0, i);
            }
            fos.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
