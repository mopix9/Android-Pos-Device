package com.fanap.corepos.tms.model

data class Update_Result(

    val lastVersionNo: String?,
    val isUpdateForce: Boolean?,
    val downloadUrl: String?,
    val responseDesc: String?,
    val responseCode: Int?

)
