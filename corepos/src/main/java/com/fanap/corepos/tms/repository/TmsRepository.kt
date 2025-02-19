package com.fanap.corepos.tms.repository

import com.fanap.corepos.tms.utils.TmsConstants
import com.fanap.corepos.tms.utils.TmsRetrofitBuilder
import com.fanap.corepos.tms.model.*
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class TmsRepository @Inject constructor() {

    private lateinit var service : TmsService

    suspend fun login(arg: Login_Arg , tmsAddress : String) : Response<Login_Result?>? {
        return try {
            service  = TmsRetrofitBuilder.getTmsServices(TmsConstants.TOKEN,tmsAddress)
            service.login(arg)
        }catch (e : Exception){
            null
        }
    }

    suspend fun getTerminal(arg: Termianl_Arg, tmsAddress : String) : Response<Terminal_Result?>? {
        return try {
            service  = TmsRetrofitBuilder.getTmsServices(TmsConstants.TOKEN,tmsAddress)
            service.getTerminal(arg)
        }catch (e : Exception){
            null
        }
    }

    suspend fun getLastVersion(arg: Update_Arg, tmsAddress : String) : Response<Update_Result?>? {
        return try {
            service  = TmsRetrofitBuilder.getTmsServices(TmsConstants.TOKEN,tmsAddress)
            service.getLastVersion(arg)
        }catch (e : Exception){
            null
        }
    }


    suspend fun updateVersionNo(arg: UpdateVersion_Arg, tmsAddress : String) : Response<UpdateVersion_Result?>? {
        return try {
            service  = TmsRetrofitBuilder.getTmsServices(TmsConstants.TOKEN,tmsAddress)
            service.updateVersionNo(arg)
        }catch (e : Exception){
            null
        }
    }

    suspend fun getConfig(arg: Config_Arg, tmsAddress : String) : Response<Config_Result?>? {
        return try {
            service  = TmsRetrofitBuilder.getTmsServices(TmsConstants.TOKEN,tmsAddress)
            service.getConfig(arg)
        }catch (e : Exception){
            null
        }
    }

}