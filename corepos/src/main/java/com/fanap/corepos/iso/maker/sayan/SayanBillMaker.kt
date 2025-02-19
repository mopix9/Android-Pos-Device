package com.fanap.corepos.iso.maker.sayan

import com.fanap.corepos.iso.maker.base.Maker
import com.fanap.corepos.iso.model.IsoMsg
import com.fanap.corepos.iso.utils.IsoFields

class SayanBillMaker : Maker() {

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
            setField(14,msg[IsoFields.CardExpireDate])
            setField(34,msg[IsoFields.Serial])
            setField(35,msg[IsoFields.Track2])
            setField(41,msg[IsoFields.Terminal])
            setField(42,msg[IsoFields.Merchant])
            setField(48,msg[IsoFields.BillId] + "~" + msg[IsoFields.PayId])
            setField(52,msg[IsoFields.PinBlock])
        }

        return iso
    }
}