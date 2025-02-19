package com.masa.aryan.presentation;

import android.annotation.SuppressLint;
import android.app.Presentation;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;

/**
 * Created by @author chencz on 2018/7/17.
 */

@SuppressLint("NewApi")
public abstract class BasePresentation extends Presentation implements CreatePresentationListener{

    @SuppressLint("NewApi")
    public BasePresentation(Context outerContext, Display display) {
        super(outerContext, display);
    }

    @SuppressLint("NewApi")
    public BasePresentation(Context outerContext, Display display, int theme) {
        super(outerContext, display);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
//        int orientation = this.getResources().getConfiguration().orientation;
//        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
//            Log.e("ccz","Presentation横屏");
//        } else if (orientation == Configuration.ORIENTATION_PORTRAIT) {
//            Log.e("ccz","Presentation竖屏");
//        }
        initWindowAttribute();
        initView();
        initData();
    }

    @Override
    protected void onStop() {
        try{
            super.onStop();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    protected void onStart() {
        try{
            super.onStart();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void cancel(){
        try{
            super.cancel();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
