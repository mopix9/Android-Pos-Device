package com.fanap.corepos.iso.maker.base

import android.util.ArrayMap
import com.fanap.corepos.iso.utils.IsoFields

interface IMakerFactory {
    fun getIsoMaker(msg: HashMap<IsoFields, String>) : Maker
}