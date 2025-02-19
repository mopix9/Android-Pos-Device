package com.fanap.corepos.iso.unpacker.aryan.base

import com.fanap.corepos.iso.unpacker.base.IUnPackerFactory
import com.fanap.corepos.iso.unpacker.base.UnPacker
import com.fanap.corepos.iso.unpacker.aryan.*
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.utils.IsoUtil
import java.lang.IllegalArgumentException

class AryanUnPackerFactory : IUnPackerFactory {

    override fun getUnPacker(msg: ByteArray): UnPacker {

        val message = IsoUtil.bytesToHex(msg).toCharArray()

        return when (getProcessCode(message)) {
            "220000" -> AryanTopupUnPacker()
            "170000" -> AryanBillUnPacker()
            "180000" -> AryanVoucherUnPacker()
            "310000" -> AryanBalanceUnPacker()
            "920000" -> AryanLogonUnPacker()
            "930000" -> AryanGetTerminalUnPacker()
            "190000" -> AryanBillInquiryUnPacker()
            "590000" -> AryanRollUnPacker()
            "000000" -> {
                when (getMti(message)){
                    "0210" -> AryanBuyUnPacker()
                    "0230" -> AryanAdviseUnPacker()
                    "0430" -> AryanReverseUnPacker()
                    else -> throw IllegalArgumentException()
                }

            }
            else -> throw IllegalArgumentException("Undefined MTI!")
        }
    }

    private fun getProcessCode(msg : CharArray) : String {
        return if (hasPan(msg))
            msg.sliceArray(52 until 58).concatToString()
        else
            msg.sliceArray(34 until 40).concatToString()
    }

    private fun getMti(msg : CharArray) = msg.sliceArray(14 until 18).concatToString()

    private fun hasPan(msg : CharArray) : Boolean{
        val bitmapString = msg.sliceArray(18..33).concatToString()
        val bitmap: List<Int> = IsoUtil.unpackBitmap(bitmapString)
        return bitmap[1] == 1
    }

}