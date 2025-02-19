package com.fanap.sina.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.fanap.corepos.database.aryan.UserRepository
import com.fanap.corepos.database.base.ISettingsRepository
import com.fanap.corepos.database.base.ITransactionRepository
import com.fanap.corepos.database.base.IUserRepository
import com.fanap.corepos.database.service.model.SettingsNames
import com.fanap.corepos.database.service.model.Transaction
import com.fanap.corepos.database.service.model.User
import com.fanap.corepos.device.hsm.HSMInterface
import com.fanap.corepos.di.DependencyManager
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.utils.sina.SinaUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.HashMap
import kotlin.properties.Delegates

open class BaseViewModel(application: Application) : AndroidViewModel(application) {

    private val transactionRepository: ITransactionRepository by lazy { DependencyManager.provideTransactionRepository(getApplication()) }
    private val settingsRepository: ISettingsRepository by lazy { DependencyManager.provideSettingsRepository(getApplication()) }
    private val userRepository:IUserRepository by lazy { DependencyManager.provideUserRepository(getApplication()) }

    lateinit var serial : String
    lateinit var terminal : String
    lateinit var merchant : String
    lateinit var name : String
    lateinit var merchantPhone : String
    lateinit var stanList : List<Int>
    var userId by Delegates.notNull<Long>()
   lateinit var user: User

    init {
        viewModelScope.launch {
            //transactionRepository.maskLastTransaction()
            stanList = transactionRepository.getStanSet()
            serial = settingsRepository.getValue(SettingsNames.TerminalSerial.name)?.value ?: ""
            terminal = settingsRepository.getValue(SettingsNames.TerminalNo.name)?.value ?: ""
            name = settingsRepository.getValue(SettingsNames.MerchantName.name)?.value ?: ""
            merchant = settingsRepository.getValue(SettingsNames.MerchantNo.name)?.value ?: ""
            merchantPhone = settingsRepository.getValue(SettingsNames.Phone.name)?.value ?: ""
            userId = userRepository.getAllUserId()!!.userId!!
        }
    }

    suspend fun insertTransaction(map : HashMap<IsoFields, String>) = viewModelScope.async(
        Dispatchers.IO) {
        return@async transactionRepository.insert(map)
    }.await()

    suspend fun updateTransaction(transaction: Transaction) = viewModelScope.async(Dispatchers.IO) {
        return@async transactionRepository.updateTransaction(transaction)
    }.await()

 suspend fun updateWithUserId(userId:Long) = viewModelScope.async(Dispatchers.IO) {
  return@async transactionRepository.updateWithUserId(userId)
 }.await()

    open fun makeAdvice(result: HashMap<IsoFields, String>, track2: String) = HashMap<IsoFields, String>().apply {
        put(IsoFields.Mti,"0220")
        put(IsoFields.CardNumber,track2.take(16))
        put(IsoFields.ProcessCode,"000000")
        put(IsoFields.Amount, result[IsoFields.Amount] ?: "0")
        put(IsoFields.LastStan, stanList[0].toString())
        put(IsoFields.Stan, (stanList[1] + 1).toString())
        put(IsoFields.TransactionTime, SinaUtils.getTime())
        put(IsoFields.TransactionDate, SinaUtils.getDate())
        put(IsoFields.Serial, serial)
        put(IsoFields.Terminal, terminal)
        put(IsoFields.Merchant, merchant)
        put(IsoFields.TransactionDateTime, result[IsoFields.TransactionDate] + result[IsoFields.TransactionTime])
        put(IsoFields.TransactionStan, result[IsoFields.Stan]?:"")
        put(IsoFields.TransactionRrn, result[IsoFields.Rrn]?:"")
    }

    open fun makeReverse(transaction: Transaction) = HashMap<IsoFields, String>().apply {
        put(IsoFields.Mti,"0400")
        put(IsoFields.CardNumber, transaction.card ?: "")
        put(IsoFields.ProcessCode,"000000")
        put(IsoFields.Amount, transaction.amount ?: "")
        put(IsoFields.LastStan, stanList[0].toString())
        put(IsoFields.Stan, (stanList[1] + 1).toString())
        put(IsoFields.TransactionTime, SinaUtils.getTime())
        put(IsoFields.TransactionDate, SinaUtils.getDate())
        put(IsoFields.Serial, serial)
        put(IsoFields.Terminal, terminal)
        put(IsoFields.Merchant, merchant)
        put(IsoFields.TransactionDateTime, transaction.date ?: "")
        put(IsoFields.TransactionStan,transaction.stan ?: "")
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}