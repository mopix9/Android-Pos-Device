package com.fanap.corepos.iso.maker.aryan

import com.fanap.corepos.iso.maker.base.Maker
import com.fanap.corepos.iso.model.IsoMsg
import com.fanap.corepos.iso.utils.IsoFields

class AryanBillMaker : Maker() {

    override fun makeIso(msg: HashMap<IsoFields, String>): IsoMsg {
        val iso = IsoMsg()
        iso.apply {
            setField(0,msg[IsoFields.Mti])
            setField(3,msg[IsoFields.ProcessCode])
            setField(4,msg[IsoFields.Amount])
            setField(11,msg[IsoFields.Stan])
            setField(12,msg[IsoFields.TransactionTime])
            setField(13,msg[IsoFields.TransactionDate])
            setField(22,msg[IsoFields.InformationEntryMode])
            setField(24,msg[IsoFields.NiiCode])
            setField(25,msg[IsoFields.ConditionCode])
            setField(35,msg[IsoFields.Track2])
            setField(41,msg[IsoFields.Terminal])
            setField(42,msg[IsoFields.Merchant])
            setField(48,msg[IsoFields.Serial]+ "~" +msg[IsoFields.SoftwareVersion]+ "~" +msg[IsoFields.TerminalLanguageCode]+ "~" +msg[IsoFields.BillId]+ "~"+ msg[IsoFields.PayId]+ "~" +msg[IsoFields.ConnectionType] )
            setField(49,msg[IsoFields.CurrencyCode])
            setField(52,msg[IsoFields.PinBlock])
        }

        return iso
    }
}