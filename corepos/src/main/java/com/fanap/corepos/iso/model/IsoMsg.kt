package com.fanap.corepos.iso.model

import java.util.*

class IsoMsg {

    var fields: TreeMap<Int, String?> = TreeMap()

    fun setField(fieldNo: Int, value: String?) {
        fields[fieldNo] = value
    }

    fun getField(fieldNo: Int): String? {
        return fields[fieldNo]
    }

    fun clone(msg: IsoMsg) {
        fields = TreeMap()
        for ((key, value) in msg.fields) fields[key] =
            value

    }
}