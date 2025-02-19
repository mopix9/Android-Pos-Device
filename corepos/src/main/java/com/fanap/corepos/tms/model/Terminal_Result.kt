package com.fanap.corepos.tms.model

data class Terminal_Result(
     val terminalNo: String,
     val merchantNo: String ,
     val switchIpAddress: String,
     val switchPort: String,
     val responseDesc: String,
     val responseCode: Int

)
