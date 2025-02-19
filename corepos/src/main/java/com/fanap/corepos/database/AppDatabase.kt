package com.fanap.corepos.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.fanap.corepos.database.aryan.AryanTransactionDao
import com.fanap.corepos.database.common.PosLogDao
import com.fanap.corepos.database.common.SettingsDao
import com.fanap.corepos.database.common.ShiftDao
import com.fanap.corepos.database.common.UserDao
import com.fanap.corepos.database.service.model.PosLog
import com.fanap.corepos.database.service.model.Settings
import com.fanap.corepos.database.service.model.SettingsNames
import com.fanap.corepos.database.service.model.Shift
import com.fanap.corepos.database.service.model.Transaction
import com.fanap.corepos.database.service.model.User
import com.fanap.corepos.database.sina.SinaTransactionDao
import com.fanap.corepos.di.DependencyManager
import com.fanap.corepos.di.IsoProtocol
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

@Database(
    entities = [Transaction::class, PosLog::class, Settings::class, Shift::class , User::class],
    version = 6
)
abstract class AppDatabase : RoomDatabase(), CoroutineScope {

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    abstract fun sinaTransactionDao(): SinaTransactionDao
//    abstract fun dotinTransactionDao(): DotinTransactionDao
    abstract fun aryanTransactionDao(): AryanTransactionDao

    abstract fun userDao():UserDao


    abstract fun settingsDao(): SettingsDao

    abstract fun shiftDao(): ShiftDao

    abstract fun posLogDao(): PosLogDao

    companion object {
        var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "db").addCallback( object : Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)
                                initDatabase()
                            }
                        }).build()
                    }
                }
            }
            return INSTANCE
        }

        private fun initDatabase() {
            INSTANCE?.launch {
                INSTANCE!!.settingsDao().insert(
//                    Settings(SettingsNames.TerminalSerial.name, DeviceSDKManager.getSerialSmartPeak()?.serial ?: "0000000000"))
//                    TODO TIANYU SMARTPEAK NEWLAND
                    Settings(SettingsNames.TerminalSerial.name, "00001504P6000003690" ))
//                    Settings(SettingsNames.TerminalSerial.name, DeviceSDKManager.getSerialInterface()?.serial ?: "0000000000"))
                when(DependencyManager.protocol){
                    IsoProtocol.ARYAN -> {
                        INSTANCE!!.settingsDao().insert(Settings(SettingsNames.Ip.name, "87.107.134.151"))
                        INSTANCE!!.settingsDao().insert(Settings(SettingsNames.Port.name, "5050"))
                        INSTANCE!!.settingsDao().insert(Settings(SettingsNames.Nii.name, "0768"))
                    }

                    IsoProtocol.FANAVA -> {
                        INSTANCE!!.settingsDao().insert(Settings(SettingsNames.Ip.name, "78.157.33.208"))
                        INSTANCE!!.settingsDao().insert(Settings(SettingsNames.Port.name, "4142"))
                        INSTANCE!!.settingsDao().insert(Settings(SettingsNames.Nii.name, "09"))
                    }
                    else -> {}
                }

                delay(1500)
            }

        }

    }
}
