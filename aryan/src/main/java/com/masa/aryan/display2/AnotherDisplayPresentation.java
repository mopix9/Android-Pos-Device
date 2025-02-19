/*
package com.masa.aryan.display2;

import android.app.Presentation;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.view.Display;

import androidx.annotation.NonNull;

import com.masa.aryan.databinding.ActivityMainPresentationBinding;



public class AnotherDisplayPresentation extends Presentation {


    private ActivityMainPresentationBinding presentationBinding;



    public AnotherDisplayPresentation(Context outerContext) {
        super(outerContext, getDisplay2(outerContext));
    }

    public  Display getDisplay2(@NonNull Context context) {
        DisplayManager displayManager = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
        Display[] displays = displayManager.getDisplays();
        if (displays.length > 1) {
            return displays[1];
        } else {
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presentationBinding = ActivityMainPresentationBinding.inflate(getLayoutInflater());
        setContentView(presentationBinding.getRoot());
    }

}
*/
