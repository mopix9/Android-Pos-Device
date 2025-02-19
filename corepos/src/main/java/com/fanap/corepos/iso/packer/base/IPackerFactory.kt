package com.fanap.corepos.iso.packer.base

import android.util.ArrayMap
import com.fanap.corepos.iso.utils.IsoFields

interface IPackerFactory {
    fun getPacker(msg: HashMap<IsoFields, String>) : Packer
}