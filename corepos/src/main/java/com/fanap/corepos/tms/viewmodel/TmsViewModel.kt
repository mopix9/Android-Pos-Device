package com.fanap.corepos.tms.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fanap.corepos.database.base.ISettingsRepository
import com.fanap.corepos.database.service.model.SettingsNames
import com.fanap.corepos.di.DependencyManager
import com.fanap.corepos.tms.model.*
import com.fanap.corepos.tms.utils.TmsConstants
import com.fanap.corepos.tms.repository.TmsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.http.Body
import javax.inject.Inject

@HiltViewModel
class TmsViewModel @Inject constructor(private val repository: TmsRepository, application: Application
) : AndroidViewModel(application) {

    private val settingsRepository: ISettingsRepository by lazy { DependencyManager.provideSettingsRepository(application) }

    fun init() {

    }

    suspend fun getConfig(arg: Config_Arg): Config_Result? {
        return try {
            val tmsAddress = settingsRepository.getValue(SettingsNames.Tms.name)?.value ?: ""
            val response = repository.getConfig(arg,tmsAddress)
            if (response?.code() == 401) {
                getToken(tmsAddress)
                repository.getConfig(arg,tmsAddress)?.body()
            } else
                response?.body()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getLastVersion(arg: Update_Arg): Update_Result? {
        return try {
            val tmsAddress = settingsRepository.getValue(SettingsNames.Tms.name)?.value ?: ""
            val response = repository.getLastVersion(arg,tmsAddress)
            if (response?.code() == 401) {
                getToken(tmsAddress)
                repository.getLastVersion(arg,tmsAddress)?.body()
            } else
                response?.body()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun updateVersionNo(arg: UpdateVersion_Arg): UpdateVersion_Result? {
        return try {
            val tmsAddress = settingsRepository.getValue(SettingsNames.Tms.name)?.value ?: ""
            val response = repository.updateVersionNo(arg,tmsAddress)
            if (response?.code() == 401) {
                getToken(tmsAddress)
                repository.updateVersionNo(arg,tmsAddress)?.body()
            } else
                response?.body()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }


    private suspend fun getToken(tmsAddress : String) {
        val login = repository.login(
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