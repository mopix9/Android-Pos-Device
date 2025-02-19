package com.fanap.corepos.di

import android.content.Context
import com.fanap.corepos.database.aryan.AryanTransactionRepository
import com.fanap.corepos.database.aryan.ShiftRepository
import com.fanap.corepos.database.aryan.UserRepository
import com.fanap.corepos.database.base.ISettingsRepository
import com.fanap.corepos.database.base.IShiftRepository
import com.fanap.corepos.database.base.ITransactionRepository
import com.fanap.corepos.database.base.IUserRepository
import com.fanap.corepos.database.common.SettingsRepository
import com.fanap.corepos.iso.IIso
import com.fanap.corepos.iso.maker.aryan.base.AryanIsoMakerFactory
import com.fanap.corepos.iso.maker.base.IMakerFactory
import com.fanap.corepos.iso.maker.dotin.base.DotinIsoMakerFactory
import com.fanap.corepos.iso.manager.aryan.AryanTransaction
import com.fanap.corepos.iso.manager.dotin.DotinTransaction
import com.fanap.corepos.iso.packer.aryan.base.AryanPackerFactory
import com.fanap.corepos.iso.packer.base.IPackerFactory
import com.fanap.corepos.iso.packer.dotin.base.DotinPackerFactory
import com.fanap.corepos.iso.unpacker.aryan.base.AryanUnPackerFactory
import com.fanap.corepos.iso.unpacker.base.IUnPackerFactory
import com.fanap.corepos.iso.unpacker.dotin.base.DotinUnPackerFactory
import javax.net.ssl.SSLContext

object DependencyManager {

    lateinit var protocol : IsoProtocol
    //Sayan Test
//    var ip = "79.127.47.105"
//    var port = 16000

/*
    var ip = "87.107.134.117"
    var port = 3330
*/

    //Aryan Operational Address:
    var ip = "87.107.134.151"
    var port = 5050
    var nii = "0768"

    var sslContext : SSLContext? = null

    //FanAva
//    var ip = "78.157.33.208"
//    var port = 4142


    fun provideIsoTransaction() : IIso{
        return when (protocol){
            IsoProtocol.DOTIN -> DotinTransaction()
//            IsoProtocol.SAYAN -> SayanTransaction()
            IsoProtocol.ARYAN -> AryanTransaction()
            else -> AryanTransaction()
        }
    }

    fun provideIsoMakerFactory() : IMakerFactory{
        return when (protocol){
            IsoProtocol.DOTIN -> DotinIsoMakerFactory()
            IsoProtocol.ARYAN -> AryanIsoMakerFactory()
            else -> AryanIsoMakerFactory()
        }
    }

    fun provideIsoPackerFactory() : IPackerFactory{
        return when (protocol){
            IsoProtocol.DOTIN -> DotinPackerFactory()
            IsoProtocol.ARYAN -> AryanPackerFactory()
            else -> AryanPackerFactory()
        }
    }

    fun provideIsoUnPackerFactory() : IUnPackerFactory{
        return when (protocol){
            IsoProtocol.DOTIN -> DotinUnPackerFactory()
            IsoProtocol.ARYAN -> AryanUnPackerFactory()
            else -> AryanUnPackerFactory()
        }
    }
    fun provideTransactionRepository(context: Context) : ITransactionRepository {
        return when (protocol){

            IsoProtocol.ARYAN -> AryanTransactionRepository(context)
            else -> AryanTransactionRepository(context)
        }
    }

    fun provideShiftRepository(context: Context):IShiftRepository{

        return ShiftRepository(context)
    }

    fun provideUserRepository(context: Context):IUserRepository{
        return UserRepository(context)
    }

    fun provideSettingsRepository(context: Context) : ISettingsRepository {
//        return when (protocol){
//            IsoProtocol.SINA -> SinaTransactionRepository()
//            IsoProtocol.DOTIN -> DotinTransactionRepository()
//        }
        return SettingsRepository(context)
    }

}