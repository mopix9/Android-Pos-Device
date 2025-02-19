package com.fanap.corepos.device.print.xcheng;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.fanap.corepos.device.print.PrinterInterface;
import com.pos.sdk.printer.POIPrinterManager;
import com.pos.sdk.printer.PosPrinter;
import com.pos.sdk.printer.PosPrinterInfo;
import com.pos.sdk.printer.models.BitmapPrintLine;

import java.util.HashMap;
import java.util.List;

import kotlin.coroutines.Continuation;

public class XchengPrinter extends Thread implements PrinterInterface {
    private Context context;
    private PrinterListener printer_callback = new PrinterListener();
    private POIPrinterManager  printerManage;
    private Bitmap bitmapReceipt;
    private MutableLiveData<Boolean> mutablePrintIsSuccess = new MutableLiveData<>();


    public XchengPrinter(Context context) {
        printerManage = new POIPrinterManager(context);
        this.context = context;
    }


    @Override
    public void run() {
        super.run();

        printerManage.open();
        printerManage.cleanCache();
        printerManage.setPrintGray(130);
        printerManage.setLineSpace(0);
        printerManage.lineWrap(0);
        BitmapPrintLine bitmapPrintLine = new BitmapPrintLine();

        if (bitmapReceipt != null) {
            bitmapPrintLine.setBitmap(bitmapReceipt);
            printerManage.addPrintLine(bitmapPrintLine);
            printerManage.beginPrint(printer_callback);
            //printerManage.feedPaper(1);
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
        PosPrinterInfo info = new PosPrinterInfo();
        PosPrinter.getPrinterInfo(0, info);
        return info.mHavePaper > 0;
    }

    @Nullable
    @Override
    public Object printBitmap(@NonNull Bitmap bitmap, @NonNull Continuation<? super Boolean> $completion) {
        return null;
    }

    class PrinterListener implements POIPrinterManager .IPrinterListener {
        private final String TAG = "Print";

        @Override
        public void onStart() {
            Log.d("xcheng_printer","start print");
        }

        @Override
        public void onFinish() {
            try {
                Log.d("xcheng_printer","pint success");
                mutablePrintIsSuccess.postValue(true);
                recycle();
                printerManage.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        @Override
        public void onError(int errorCode, String detail) {
            try {
                Log.d("xcheng_printer", "print error" + " errorcode = " + errorCode + " detail = " + detail);
                mutablePrintIsSuccess.postValue(false);
                recycle();
                printerManage.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void recycle(){
        bitmapReceipt.recycle();
        bitmapReceipt = null;
    }


}
