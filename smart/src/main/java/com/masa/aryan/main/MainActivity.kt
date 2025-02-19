package com.masa.aryan.main

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.fanap.corepos.database.base.ISettingsRepository
import com.fanap.corepos.database.service.model.SettingsNames
import com.fanap.corepos.device.DeviceSDKManager
import com.fanap.corepos.device.NewLandSetup
import com.fanap.corepos.device.beep.BeepInterface
import com.fanap.corepos.device.beep.BeepType
import com.fanap.corepos.device.hsm.HSMInterface
import com.fanap.corepos.device.hsm.aryan.newland.NewLandHSMAryan
import com.fanap.corepos.di.DependencyManager
import com.fanap.corepos.utils.IsoUtil
import com.masa.aryan.R
import com.masa.aryan.utils.SSLInitiator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var appContext: Context

    private val settingsRepository: ISettingsRepository by lazy {
        DependencyManager.provideSettingsRepository(appContext)
    }

    var onTouchEvent = MutableLiveData(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        onTouchEvent.postValue(true)
        return super.dispatchTouchEvent(ev)
    }
}