package com.fanap.corepos.tms.model

data class Config_Result(

    var configLst: List<ConfigList> ,
    var responseDesc: String ,
var responseCode: Int

)


class ConfigList {
    var name: String? = null
    var value: String? = null
}
