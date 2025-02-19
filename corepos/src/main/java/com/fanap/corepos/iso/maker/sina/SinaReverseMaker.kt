package com.fanap.corepos.iso.maker.sina

import android.util.ArrayMap
import com.fanap.corepos.iso.maker.base.Maker
import com.fanap.corepos.iso.model.IsoMsg
import com.fanap.corepos.iso.utils.IsoFields

class SinaReverseMaker : Maker() {

    override fun makeIso(msg: HashMap<IsoFields, String>): IsoMsg {
        val iso = IsoMsg()
        iso.apply {
            setField(0,msg[IsoFields.Mti])
            setField(2,msg[IsoFields.CardNumber])
            setField(3,msg[IsoFields.ProcessCode])
            setField(4,msg[IsoFields.Amount])
            setField(8,msg[IsoFields.LastStan])
            setField(11,msg[IsoFields.Stan])
            setField(12,msg[IsoFields.TransactionTime])
            setField(13,msg[IsoFields.TransactionDate])
            setField(34,msg[IsoFields.Serial])
            setField(41,msg[IsoFields.Terminal])
            setField(42,msg[IsoFields.Merchant])
            setField(48,msg[IsoFields.TransactionDateTime] + "~" + msg[IsoFields.TransactionStan])
        }

        return iso
    }
}