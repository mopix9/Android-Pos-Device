package com.fanap.corepos.iso.maker.aryan

import com.fanap.corepos.iso.maker.base.Maker
import com.fanap.corepos.iso.model.IsoMsg
import com.fanap.corepos.iso.utils.IsoFields

class AryanLogonMaker : Maker() {

    override fun makeIso(msg: HashMap<IsoFields, String>): IsoMsg {
        val iso = IsoMsg()
        iso.apply {
            setField(0,msg[IsoFields.Mti])
            setField(3,msg[IsoFields.ProcessCode])
            setField(11,msg[IsoFields.Stan])
            setField(12,msg[IsoFields.TransactionTime])
            setField(13,msg[IsoFields.TransactionDate])
            setField(24,msg[IsoFields.NiiCode])
            setField(48,msg[IsoFields.Serial]+ "~" +msg[IsoFields.SoftwareVersion]+ "~" +msg[IsoFields.TerminalLanguageCode]/*+ "~" +msg[IsoFields.ConnectionType] */)
        }

        return iso
    }
}