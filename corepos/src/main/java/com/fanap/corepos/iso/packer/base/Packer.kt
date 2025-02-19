package com.fanap.corepos.iso.packer.base

import java.util.*

abstract class Packer {

    lateinit var message: TreeMap<Int, String?>

    abstract fun pack(): ByteArray?
}