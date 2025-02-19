package com.fanap.corepos.tms.repository

import com.fanap.corepos.tms.model.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TmsService {

    @POST("api/user/login")
    suspend fun login(@Body arg: Login_Arg): Response<Login_Result?>?

    @POST("api/terminalmanage/getterminal")
    suspend fun getTerminal(@Body arg: Termianl_Arg): Response<Terminal_Result?>?

    @POST("api/terminalmanage/GetLastVersion")
    suspend fun getLastVersion(@Body arg: Update_Arg): Response<Update_Result?>?

    @POST("api/TerminalManage/UpdateVersionNo")
    suspend fun updateVersionNo(@Body arg: UpdateVersion_Arg): Response<UpdateVersion_Result?>?

//    @POST("api/terminalmanage/SendError")
//    fun sendError(@Body arg: Error_Arg?): Call<Error_Result?>?

    @POST("api/terminalmanage/GetConfig")
    suspend fun getConfig(@Body arg: Config_Arg): Response<Config_Result?>?

}