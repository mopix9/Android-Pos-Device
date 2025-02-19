package com.fanap.corepos.iso.unpacker.base

interface IUnPackerFactory {
    fun getUnPacker(msg: ByteArray) : UnPacker
}