/*
package com.masa.aryan.display2

import android.app.Presentation
import android.content.Context
import android.hardware.display.DisplayManager
import android.os.Bundle
import android.view.Display
import com.masa.aryan.databinding.ActivityMainPresentationBinding

fun getDisplay2(context: Context): Display? {
    val displayManager = context.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
    val displays = displayManager.displays
    return if (displays.size > 1) {
        displays[1]
    } else {
        null
    }
}

class AnotherDisplayPresentation(outerContext: Context) :
    Presentation(outerContext, getDisplay2(outerContext)) {
    private var presentationBinding: ActivityMainPresentationBinding? = null
    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        presentationBinding = ActivityMainPresentationBinding.inflate(layoutInflater)
        setContentView(presentationBinding!!.root)
    }
}
*/
