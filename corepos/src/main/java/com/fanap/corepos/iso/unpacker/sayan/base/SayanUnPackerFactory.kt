package com.fanap.corepos.iso.unpacker.sayan.base

import com.fanap.corepos.iso.unpacker.base.IUnPackerFactory
import com.fanap.corepos.iso.unpacker.base.UnPacker
import com.fanap.corepos.iso.unpacker.sayan.*
import com.fanap.corepos.iso.unpacker.sayan.SayanBalanceUnPacker
import com.fanap.corepos.iso.unpacker.sayan.SayanLogonUnPacker
import com.fanap.corepos.iso.unpacker.sayan.SayanVoucherUnPacker
import com.fanap.corepos.utils.IsoUtil
import java.lang.IllegalArgumentException

class SayanUnPackerFactory : IUnPackerFactory {

    override fun getUnPacker(msg: ByteArray): UnPacker {

        val message = IsoUtil.bytesToHex(msg).toCharArray()

        return when (getProcessCode(message)) {
            "220000" -> SayanTopupUnPacker()
            "170000" -> SayanBillUnPacker()
            "180000" -> SayanVoucherUnPacker()
            "310000" -> SayanBalanceUnPacker()
            "920000" -> SayanLogonUnPacker()
            "930000" -> SayanGetTerminalUnPacker()
            "000000" -> {
                when (getMti(message)){
                    "0210" -> SayanBuyUnPacker()
                    "0230" -> SayanAdviseUnPacker()
                    "0430" -> SayanReverseUnPacker()
                    else -> throw IllegalArgumentException()
                }

            }
            else -> throw IllegalArgumentException("Undefined MTI!")
        }
    }

    private fun getProcessCode(msg : CharArray)  = msg.sliceArray(34 until 40).concatToString()

    private fun getMti(msg : CharArray) = msg.sliceArray(13 until 16).concatToString()

}