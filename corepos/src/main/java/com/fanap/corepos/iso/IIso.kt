package com.fanap.corepos.iso

import com.fanap.corepos.iso.utils.IsoFields

interface IIso {
     suspend fun doTransaction(msg : HashMap<IsoFields, String>) : HashMap<IsoFields,String>?
}