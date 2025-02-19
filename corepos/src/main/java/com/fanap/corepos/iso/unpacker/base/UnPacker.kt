package com.fanap.corepos.iso.unpacker.base

import com.fanap.corepos.iso.utils.IsoFields
import java.util.*
import kotlin.collections.HashMap

abstract class UnPacker {

    lateinit var message: ByteArray

    abstract fun unpack(): HashMap<IsoFields, String>

    fun getFieldWithTagName(buffer: String?, tagName: String): String {
        return try {
            var fieldLength = buffer?.split(tagName)?.get(0)?.takeLast(2)?.toInt() ?: 0
            fieldLength = (fieldLength - 1) * 2
            if (buffer != null && fieldLength != null)
                buffer.substringAfter(tagName).take(fieldLength)
            else
                ""
        } catch (e: Exception) {
            ""
        }
    }

    fun extractFields(buffer: String): TreeMap<String, String>? =
        try {
            val list = TreeMap<String, String>()
            var tempBuffer = buffer

            while (tempBuffer.isNotEmpty()) {
                val size = tempBuffer.take(2).toInt() * 2 - 2
                tempBuffer = tempBuffer.drop(2)
                val tag = tempBuffer.take(2)
                tempBuffer = tempBuffer.drop(2)
                list[tag] = tempBuffer.take(size)
                tempBuffer = tempBuffer.drop(size)
            }

            list
        } catch (e: Exception) {
            null
        }
}