package com.fanap.corepos.iso.manager.dotin

import android.util.ArrayMap
import androidx.lifecycle.LiveData
import com.fanap.corepos.iso.IIso
import com.fanap.corepos.iso.utils.IsoFields

class DotinTransaction : IIso {

    override suspend fun doTransaction(msg: HashMap<IsoFields, String>): HashMap<IsoFields, String>? {
        TODO("Not yet implemented")
    }
}