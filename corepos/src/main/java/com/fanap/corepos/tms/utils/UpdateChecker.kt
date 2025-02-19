package com.fanap.corepos.tms.utils

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.fanap.corepos.database.base.ISettingsRepository
import com.fanap.corepos.database.service.model.SettingsNames
import com.fanap.corepos.device.DeviceSDKManager
import com.fanap.corepos.di.DependencyManager
import com.fanap.corepos.tms.model.Login_Arg
import com.fanap.corepos.tms.model.Update_Arg
import com.fanap.corepos.tms.repository.TmsRepository
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

object UpdateChecker : CoroutineScope {

    private val TAG = this::class.java.simpleName
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + Job()

    private lateinit var tmsRepository: TmsRepository
    private lateinit var settingsRepository: ISettingsRepository
    var onUpdateReceived = MutableLiveData("")

    fun check(context: Context,version : String) {
        cancel()
        tmsRepository = TmsRepository()
        settingsRepository = DependencyManager.provideSettingsRepository(context)
        val serial = DeviceSDKManager.getSerialInterface()?.serial ?: ""

        launch(Dispatchers.IO) {
            while (true) {
                delay(getDelay())
                val tmsAddress = settingsRepository.getValue(SettingsNames.Tms.name)?.value ?: ""
                var result = tmsRepository.getLastVersion(Update_Arg(version,serial,TmsConstants.ENCRYPTED_USERNAME,1,3),tmsAddress)
                if (result?.code() == 401) {
                    getToken(tmsAddress)
                    result = tmsRepository.getLastVersion(Update_Arg(version,serial,TmsConstants.ENCRYPTED_USERNAME,1,3),tmsAddress)
                }
                result?.body()?.let {
                    if (it.lastVersionNo ?: "" > version)
                        onUpdateReceived.postValue(it.downloadUrl)
                }
            }
        }
    }

    private suspend fun getDelay() : Long{
        settingsRepository.getValue(SettingsNames.UpdateTimer.name)?.value?.let {
            try {
                return it.toLong() * 60 * 1000
            }catch (e : Exception){}
        }
        return 60 * 60 * 1000
    }

    private suspend fun getToken(tmsAddress : String) {
        val login = tmsRepository.login(
            Login_Arg(
                TmsConstants.LOGIN_PASSWORD,
                TmsConstants.LOGIN_USERNAME,
                TmsConstants.TERMINAL_TYPE
            )
            ,tmsAddress)
        TmsConstants.TOKEN = login?.body()?.userToken ?: ""
        TmsConstants.ENCRYPTED_USERNAME = login?.body()?.encrypteUsername ?: ""
    }

}