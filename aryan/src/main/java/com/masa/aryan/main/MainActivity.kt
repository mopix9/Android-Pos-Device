package com.masa.aryan.main

import android.Manifest
import android.app.Presentation
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.display.DisplayManager
import android.os.Build
import android.os.Bundle
import android.view.Display
import android.view.MotionEvent
import android.view.WindowInsets
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.fanap.corepos.database.base.ISettingsRepository
import com.fanap.corepos.database.service.model.SettingsNames
import com.fanap.corepos.di.DependencyManager
import com.masa.aryan.R
import com.masa.aryan.databinding.ActivityMainPresentationBinding
import com.masa.aryan.utils.SSLInitiator
import com.whty.smartpos.tysmartposapi.ITYSmartPosApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    //TODO TIANYU
    var smartPosApi: ITYSmartPosApi? = null

    @Inject
    lateinit var appContext: Context

    private val settingsRepository: ISettingsRepository by lazy {
        DependencyManager.provideSettingsRepository(appContext)
    }

    var onTouchEvent = MutableLiveData(false)

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO TIANYU
        smartPosApi = ITYSmartPosApi.get(
            applicationContext
        )


//        OperationInter.setActivity(this)




        @Suppress("DEPRECATION")


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        //Initialize Connection address if enabled
        lifecycleScope.launch {
            val isSSLEnabled = settingsRepository.getValue(SettingsNames.SSL.name)?.value.toBoolean()
            SSLInitiator().init(isSSLEnabled,appContext)
            settingsRepository.getValue(SettingsNames.Ip.name)?.value?.let {
                DependencyManager.ip = it
            }
            settingsRepository.getValue(SettingsNames.Port.name)?.value?.let {
                DependencyManager.port = it.toInt()
            }
            settingsRepository.getValue(SettingsNames.Nii.name)?.value?.let {
                DependencyManager.nii = it
            }
        }
        //TODO TIANYU
        initPermission()

    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        onTouchEvent.postValue(true)
        return super.dispatchTouchEvent(ev)
    }

    //TODO TIANYU
    @RequiresApi(Build.VERSION_CODES.M)
    private fun initPermission() {
        val permissions = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        val toApplyList = ArrayList<String>()
        for (perm in permissions) {
            if (PackageManager.PERMISSION_GRANTED != checkSelfPermission(perm)) {
                toApplyList.add(perm)
            }
        }
        val tmpList = arrayOfNulls<String>(toApplyList.size)
        if (!toApplyList.isEmpty()) {
            requestPermissions(toApplyList.toArray(tmpList), 100)
        }
    }

    fun getDisplay2(context: Context): Display? {
        val displayManager = context.getSystemService(Context.DISPLAY_SERVICE) as DisplayManager
        val displays = displayManager.displays
        return if (displays.size > 1) {
            displays[1]
        } else {
            null
        }
    }

   inner class AnotherDisplayPresentation(outerContext: Context?) :
        Presentation(outerContext, getDisplay2(outerContext!!)) {
      private var presentationBinding: ActivityMainPresentationBinding? = null
      override fun onCreate(savedInstanceState: Bundle) {
          super.onCreate(savedInstanceState)
          presentationBinding = ActivityMainPresentationBinding.inflate(layoutInflater)
          setContentView(presentationBinding!!.getRoot())
      }

  }
}
