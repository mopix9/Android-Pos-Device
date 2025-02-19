package com.fanap.corepos.device.print.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import saman.zamani.persiandate.PersianDate;
import saman.zamani.persiandate.PersianDateFormat;

public class PrinterUtils {
/*
    public static Bitmap switchColor(Bitmap switchBitmap) {
        int width = switchBitmap.getWidth();
        int height = switchBitmap.getHeight();

        Bitmap newBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawBitmap(switchBitmap, new Matrix(), new Paint());

        int current_color, red, green, blue, alpha, avg = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                current_color = switchBitmap.getPixel(i, j);
                red = Color.red(current_color);
                green = Color.green(current_color);
                blue = Color.blue(current_color);
                alpha = Color.alpha(current_color);
                avg = (red + green + blue) / 3;
                if (avg >= 210) {
                    newBitmap.setPixel(i, j, Color.rgb(255, 255, 255));// white
                }
//				else if (avg < 210 && avg >= 80) { // avg<126 && avg>=115
//					newBitmap.setPixel(i, j, Color.rgb( 108, 108, 108));// grey
//				}
                else {
                    newBitmap.setPixel(i, j, Color.rgb( 0, 0, 0));// black
                }
            }
        }
        return newBitmap;
    }


    public static Bitmap scaleBitmap(Bitmap origin, int newWidth, int newHeight) {
        if (origin == null) {
            return null;
        }
        if (origin.isRecycled()) {
            return null;
        }
        int width = origin.getWidth();
        int height = origin.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        float scale = Math.max(scaleWidth, scaleHeight);
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);// 使用后乘
        Bitmap newBM = Bitmap.createBitmap(origin, 0, 0, width, height, matrix,
                false);
        if (!origin.isRecycled()) {
            origin.recycle();
        }
        return newBM;
    }

    private static void createPath(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    private static void createFile(String path, String fileName) throws IOException {
        createPath(path);
        String lastPath = path + fileName;
        File file = new File(lastPath);
        if (!file.exists()) {
            file.createNewFile();
        }
    }



    public static void copyFileToSD(Context context, String sourceFileName, String desPath, String desFileName) throws IOException {
        InputStream myInput;
        OutputStream myOutput;
        createFile(desPath, desFileName);
        myOutput = new FileOutputStream(desPath + desFileName);
        myInput = context.getAssets().open(sourceFileName);
        byte[] buffer = new byte[1024];
        int length = myInput.read(buffer);
        while (length > 0) {
            myOutput.write(buffer, 0, length);
            length = myInput.read(buffer);
        }
        myOutput.flush();
        myInput.close();
        myOutput.close();
    }

    public static String getDate(){
        //  set persian date
        PersianDate pdate = new PersianDate();
        PersianDateFormat pdformater1 = new PersianDateFormat("y/m/d");
        String date = pdformater1.format(pdate);//1396/05/20
        //PersianDateFormat pdformater2 = new PersianDateFormat("j F y");
        //String date = PersianNumber.numberConvertToPersian(pdformater2.format(pdate));//۱۹ تیر ۹۶
        return date;
    }
    public static String getTime(){
        //  first set time
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        String time = df.format(c.getTime());
        return PersianNumber.numberConvertToPersian(time);
    }

    public static String getDay(){
        //  first set time
        PersianDate pdate = new PersianDate();
        PersianDateFormat pdformater1 = new PersianDateFormat("d");
        //String date = pdformater1.format(pdate);//1396/05/20
        //PersianDateFormat pdformater2 = new PersianDateFormat("j F y");
        //String date = PersianNumber.numberConvertToPersian(pdformater1.format(pdate));


        return pdate.dayName();
    }
*/
    public static Bitmap getResizedBitmap(Bitmap bm, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        int newHeight = getHeightWithRatio(height, width, newWidth);
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }

    private static int getHeightWithRatio(int height, int oldWidth, int newWidth){
        double ratio = (double) oldWidth / newWidth;
        height = (int) (height / ratio);
        return height;
    }
}
