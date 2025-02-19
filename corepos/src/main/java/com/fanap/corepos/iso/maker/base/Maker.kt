package com.fanap.corepos.iso.maker.base

import android.util.ArrayMap
import com.fanap.corepos.iso.model.IsoMsg
import com.fanap.corepos.iso.utils.IsoFields

abstract class Maker {
    abstract fun makeIso(msg: HashMap<IsoFields, String>) : IsoMsg
}