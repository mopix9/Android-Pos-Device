package com.fanap.corepos.device.print.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;

public class CreateBitmap {

    public static Bitmap getBitmapFromView(View view) {
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.draw(canvas);
        //sizeFile(bitmap);


        return bitmap;
    }

    public static Bitmap getBitmapFromViewRotate(View view) {
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.draw(canvas);

        Matrix matrix = new Matrix();
        matrix.postRotate(-90);
        //Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, view.getMeasuredWidth(), view.getMeasuredHeight(), true);
        Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

        //sizeFile(rotatedBitmap);

        return rotatedBitmap;
    }

    public static void sizeFile(Bitmap bitmap){

        String filename = System.currentTimeMillis()+"_receipt.png";
        File sd = Environment.getExternalStorageDirectory();
        File dest = new File(sd, filename);
        try {
            FileOutputStream out = new FileOutputStream(dest);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("bitmapsize", String.valueOf(dest.length()));
    }
/*
    public static Bitmap getBitmapFromView(final View viewToDrawFrom) {
        int width = viewToDrawFrom.getWidth();
        int height = viewToDrawFrom.getHeight();
        boolean wasDrawingCacheEnabled = viewToDrawFrom.isDrawingCacheEnabled();
        if (!wasDrawingCacheEnabled)
            viewToDrawFrom.setDrawingCacheEnabled(true);
        if (width <= 0 || height <= 0) {
            if (viewToDrawFrom.getWidth() <= 0 || viewToDrawFrom.getHeight() <= 0) {
                viewToDrawFrom.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                width = viewToDrawFrom.getMeasuredWidth();
                height = viewToDrawFrom.getMeasuredHeight();
            }
            if (width <= 0 || height <= 0) {
                final Bitmap bmp = viewToDrawFrom.getDrawingCache();
                final Bitmap result = bmp == null ? null : Bitmap.createBitmap(bmp);
                if (!wasDrawingCacheEnabled)
                    viewToDrawFrom.setDrawingCacheEnabled(false);
                return result;
            }
            viewToDrawFrom.layout(0, 0, width, height);
        } else {
            viewToDrawFrom.measure(View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY));
            viewToDrawFrom.layout(0, 0, viewToDrawFrom.getMeasuredWidth(), viewToDrawFrom.getMeasuredHeight());
        }
        final Bitmap drawingCache = viewToDrawFrom.getDrawingCache();
        final Bitmap bmp = ThumbnailUtils.extractThumbnail(drawingCache, width, height);
        final Bitmap result = bmp == null || bmp != drawingCache ? bmp : Bitmap.createBitmap(bmp);
        if (!wasDrawingCacheEnabled)
            viewToDrawFrom.setDrawingCacheEnabled(false);
        return result;
    }*/
}
