package com.fanap.corepos.iso.socket

import com.fanap.corepos.di.DependencyManager
import java.io.DataOutputStream
import java.io.InputStream
import java.net.InetAddress
import java.net.Socket
import javax.net.ssl.SSLSocket
import javax.net.ssl.SSLSocketFactory


class Sender(private val msg: ByteArray) {

    fun send() : ByteArray? {

        var inputStream: InputStream? = null
        var out: DataOutputStream? = null
        var socket: Socket? = null

            try {
                socket = getSocket()
                socket.soTimeout = 20000
                socket.isConnected
                socket.receiveBufferSize = 2048
                out = DataOutputStream(socket.getOutputStream())
                out.write(msg)
                out.flush()
                inputStream = socket.getInputStream()
                val data = ByteArray(2048)
                val count = inputStream.read(data)
                return data.sliceArray(0 until count)
            } catch (e: Exception) {
                return null
            }finally {
                out?.close()
                inputStream?.close()
                socket?.close()
            }

    }

    private fun getSocket() : Socket{
        return if (DependencyManager.sslContext != null) {
            val sf: SSLSocketFactory = DependencyManager.sslContext!!.socketFactory
            sf.createSocket(InetAddress.getByName(DependencyManager.ip), DependencyManager.port) as SSLSocket
        }else{
            Socket(DependencyManager.ip, DependencyManager.port)
        }
    }

}
