package com.fanap.corepos.database.dotin

import android.content.Context
import com.fanap.corepos.database.base.ITransactionRepository
import com.fanap.corepos.database.service.model.Transaction
import com.fanap.corepos.iso.utils.IsoFields

class DotinTransactionRepository(context: Context) : ITransactionRepository {

    override suspend fun getAll(): List<Transaction>? {
        TODO("Not yet implemented")
    }

    override suspend fun getLastBuyTransaction(): Transaction? {
        TODO("Not yet implemented")
    }

    override suspend fun insert(msg: HashMap<IsoFields, String>): Transaction {
        TODO("Not yet implemented")
    }

    override suspend fun getLastValidTransaction(): Transaction? {
        TODO("Not yet implemented")
    }

    override suspend fun getLastValidTransactionForSettlement(stan: String): Transaction? {
        TODO("Not yet implemented")
    }

    override suspend fun getLastTransaction(): Transaction? {
        TODO("Not yet implemented")
    }

    override suspend fun updateTransaction(transaction: Transaction): Int {
        TODO("Not yet implemented")
    }

    override suspend fun maskLastTransaction() {
        TODO("Not yet implemented")
    }

    override suspend fun getTransaction(id: Long): Transaction? {
        TODO("Not yet implemented")
    }

    override suspend fun getTransactionByStan(stan: String): Transaction? {
        TODO("Not yet implemented")
    }

    override suspend fun getTransactionByRrn(rrn: String): Transaction? {
        TODO("Not yet implemented")
    }

    override suspend fun getTransactionsByDate(
        startDate: String,
        endDate: String
    ): List<Transaction>? {
        TODO("Not yet implemented")
    }

    override suspend fun getTransactionsByDateLazy(
        startDate: String,
        endDate: String,
        offset: Long
    ): List<Transaction>? {
        TODO("Not yet implemented")
    }

    override suspend fun getSuccessTransactions(
        startDate: String,
        endDate: String,
        processCode: String
    ): List<Transaction>? {
        TODO("Not yet implemented")
    }

    override suspend fun getSuccessTransactionsLazy(
        startDate: String,
        endDate: String,
        processCode: String,
        offset: Long
    ): List<Transaction>? {
        TODO("Not yet implemented")
    }

    override suspend fun getSuccessTransactionsLazyAll(
        startDate: String,
        endDate: String,
        offset: Long
    ): List<Transaction>? {
        TODO("Not yet implemented")
    }

    override suspend fun getSuccessTransactionsAll(
        startDate: String,
        endDate: String
    ): List<Transaction>? {
        TODO("Not yet implemented")
    }

    override suspend fun getLastPrintableTransaction(): Transaction? {
        TODO("Not yet implemented")
    }

    override suspend fun getPrintableTransaction(stan: String): Transaction? {
        TODO("Not yet implemented")
    }

    override suspend fun getSuccessCount(
        startDate: String,
        endDate: String,
        processCode: String
    ): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun getSuccessCountForShift(
        startDate: String,
        processCode: String,
        shift: Int
    ): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun getSuccessCountForUser(
        startDate: String,
        endDate: String,
        processCode: String,
        userId: Long
    ): Int? {
        TODO("Not yet implemented")
    }




    override suspend fun getFailureCount(
        startDate: String,
        endDate: String,
        processCode: String
    ): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun getFailureCountForShift(
        startDate: String,
        processCode: String,
        shift: Int
    ): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun getFailureCountForUser(
        startDate: String,
        processCode: String,
        userId: Long
    ): Int? {
        TODO("Not yet implemented")
    }



    override suspend fun getAmount(startDate: String, endDate: String, processCode: String): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun getAmountForShift(
        startDate: String,
        processCode: String,
        shift: Int
    ): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun getAmountForUser(
        startDate: String,
        endDate: String,
        processCode: String,
        userId: Long
    ): Int? {
        TODO("Not yet implemented")
    }


    override suspend fun getCount(): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun deleteFirtstXrows(count: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun delete() {
        TODO("Not yet implemented")
    }

    override suspend fun getStanSet(): List<Int> {
        TODO("Not yet implemented")
    }

    override suspend fun updateWithUserId(userId: Long) {
        TODO("Not yet implemented")
    }
}