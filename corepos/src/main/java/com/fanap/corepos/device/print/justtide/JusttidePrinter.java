package com.fanap.corepos.device.print.justtide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.RemoteException;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.fanap.corepos.device.DeviceConfig;
import com.fanap.corepos.device.print.PrinterInterface;
import com.justtide.service.dev.aidl.printer.PrinterProvider;

import java.util.HashMap;
import java.util.List;

import kotlin.coroutines.Continuation;


public class JusttidePrinter extends Thread implements PrinterInterface {
    private PrinterProvider printerProvider = null;
    private MutableLiveData<Boolean> mutablePrintIsSuccess = new MutableLiveData<>();

    Context context;
    Bitmap bitmapReceipt;
    String TAG  = "justtideprinter";

    public JusttidePrinter(Context context){
        this.context =context;

        try {
            printerProvider = DeviceConfig.deviceProvider.getPrinterProvider();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "constructor");
    }

    @Override
    public void run() {
        super.run();

        try {
            printerProvider.setGray(130);
            if (bitmapReceipt != null){
                int nRet = -1;
                nRet = printerProvider.printPicEx(bitmapReceipt, 0);
                printerProvider.freeStep(6);
                Log.d(TAG,String.valueOf(nRet));
                if (nRet == 0) {
                    mutablePrintIsSuccess.postValue(true);
                    recycle();
                    Log.d(TAG, "print true");
                }else {
                    mutablePrintIsSuccess.postValue(false);
                    recycle();
                    Log.d(TAG, "print false");
                }
            }
            Log.d(TAG, "print finish");
        } catch (RemoteException e) {
            e.printStackTrace();
            Log.d(TAG, e.getMessage());
        }

    }

    public MutableLiveData<Boolean> printBitmap(Bitmap bitmap) {
        mutablePrintIsSuccess = new MutableLiveData<>();
        bitmapReceipt = bitmap;
        start();
        return mutablePrintIsSuccess;
    }

    @Override
    public boolean hasPaper() {
        try {
            return printerProvider.getState()!=2;
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void recycle(){
        bitmapReceipt.recycle();
        bitmapReceipt = null;
    }

    @Nullable
    @Override
    public Object printBitmap(@NonNull Bitmap bitmap, @NonNull Continuation<? super Boolean> $completion) {
        return null;
    }
}
