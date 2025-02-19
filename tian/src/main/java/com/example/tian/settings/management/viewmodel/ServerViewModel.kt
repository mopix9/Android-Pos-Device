package com.example.tian.settings.management.viewmodel

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.tian.base.BaseViewModel
import com.fanap.corepos.database.service.model.Settings
import com.fanap.corepos.database.service.model.SettingsNames
import com.fanap.corepos.di.DependencyManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ServerViewModel (application: Application) : BaseViewModel(application) {

    val onError = MutableLiveData<String>()
    val onSuccess = MutableLiveData<Boolean>()

    fun setOnConfirmClicked() {
        if (observableIp.get() != "" && Patterns.IP_ADDRESS.matcher(observableIp.get()).matches()) {
            if (observablePort.get() != "") {
                if (observableNii.get() != "") {
                    update()
                } else onError.postValue("مقدار Nii را وارد کنید.")
            } else onError.postValue("شماره port را وارد کنید.")
        } else onError.postValue("یک آدرس ip معتبر وارد کنید.")
    }

    private fun update(){
        viewModelScope.launch(Dispatchers.IO) {
            settingsRepository.insert(Settings(SettingsNames.Ip.name,observableIp.get()?:""))
            settingsRepository.insert(Settings(SettingsNames.Port.name,observablePort.get()?:""))
            settingsRepository.insert(Settings(SettingsNames.Nii.name,observableNii.get()?:""))
            DependencyManager.ip = observableIp.get()!!
            DependencyManager.port = observablePort.get()!!.toInt()
            DependencyManager.nii = observableNii.get()!!
            onSuccess.postValue(true)
        }
    }
}