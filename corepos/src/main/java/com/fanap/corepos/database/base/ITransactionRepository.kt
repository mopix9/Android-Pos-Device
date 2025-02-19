package com.fanap.corepos.database.base

import com.fanap.corepos.database.service.model.Transaction
import com.fanap.corepos.iso.utils.IsoFields

interface ITransactionRepository {

    suspend fun getAll(): List<Transaction>?

    suspend fun getLastBuyTransaction(): Transaction?

    suspend fun insert(msg: HashMap<IsoFields, String>): Transaction

    suspend fun getLastValidTransaction(): Transaction?


    suspend fun getLastValidTransactionForSettlement(stan : String): Transaction?

    suspend fun getLastTransaction(): Transaction?

    suspend fun updateTransaction(transaction: Transaction): Int

    suspend fun maskLastTransaction()

    suspend fun getTransaction(id: Long): Transaction?

    suspend fun getTransactionByStan(stan: String): Transaction?

    suspend fun getTransactionByRrn(rrn: String): Transaction?

    suspend fun getTransactionsByDate(startDate: String, endDate: String): List<Transaction>?

    suspend fun getTransactionsByDateLazy(
        startDate: String,
        endDate: String,
        offset: Long
    ): List<Transaction>?

    suspend fun getSuccessTransactions(
        startDate: String,
        endDate: String,
        processCode: String
    ): List<Transaction>?

    suspend fun getSuccessTransactionsLazy(
        startDate: String,
        endDate: String,
        processCode: String,
        offset: Long
    ): List<Transaction>?

    suspend fun getSuccessTransactionsLazyAll(
        startDate: String,
        endDate: String,
        offset: Long
    ): List<Transaction>?

    suspend fun getSuccessTransactionsAll(
        startDate: String,
        endDate: String
    ): List<Transaction>?

    suspend fun getLastPrintableTransaction(): Transaction?

    suspend fun getPrintableTransaction(stan: String): Transaction?

    suspend fun getSuccessCount(startDate: String, endDate: String, processCode: String): Int?

    suspend fun getSuccessCountForShift(startDate: String, processCode: String, shift: Int): Int?

    suspend fun getSuccessCountForUser(startDate: String,endDate: String, processCode: String, userId: Long): Int?

    suspend fun getFailureCount(startDate: String, endDate: String, processCode: String): Int?

    suspend fun getFailureCountForShift(startDate: String, processCode: String, shift: Int): Int?

    suspend fun getFailureCountForUser(startDate: String, processCode: String, userId: Long): Int?

    suspend fun getAmount(startDate: String, endDate: String, processCode: String): Int?

    suspend fun getAmountForShift(startDate: String, processCode: String, shift: Int): Int?

    suspend fun getAmountForUser(startDate: String, endDate: String, processCode: String, userId: Long): Int?

    suspend fun getCount(): Int?

    suspend fun deleteFirtstXrows(count: Int)

    suspend fun delete()

    suspend fun getStanSet() : List<Int>

    suspend fun updateWithUserId(userId:Long)

}