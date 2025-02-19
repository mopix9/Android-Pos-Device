package com.fanap.corepos.database.sina

import android.content.Context
import android.util.Log
import com.fanap.corepos.database.AppDatabase
import com.fanap.corepos.database.base.ITransactionRepository
import com.fanap.corepos.database.service.model.Transaction
import com.fanap.corepos.iso.utils.IsoFields
import com.fanap.corepos.utils.Utils

class SinaTransactionRepository(context: Context) : ITransactionRepository {

    private var dao: SinaTransactionDao = AppDatabase.getDatabase(context)?.sinaTransactionDao()!!

    override suspend fun insert(msg: HashMap<IsoFields, String>): Transaction {
        val transaction = Transaction().apply {
            stan = msg[IsoFields.Stan]
            date = msg[IsoFields.TransactionDate] + msg[IsoFields.TransactionTime]
            amount = msg[IsoFields.Amount]
            rrn = msg[IsoFields.Rrn]
            mti = msg[IsoFields.Mti]
            status = msg[IsoFields.Status]
            response = msg[IsoFields.Response]
            card = msg[IsoFields.CardNumber]
            processCode = msg[IsoFields.ProcessCode]
            timeStamp = msg[IsoFields.TransactionTimeStamp]
            shift = msg[IsoFields.Shift]?.toInt()
        }
        val id = dao.insert(transaction)
        val result = dao.getTransaction(id)
        return result!!
    }

    override suspend fun getAll() = dao.getAll()

    override suspend fun getTransaction(id: Long) = dao.getTransaction(id)
    override suspend fun getTransactionByStan(stan: String): Transaction? {
        TODO("Not yet implemented")
    }

    override suspend fun getTransactionByRrn(rrn: String): Transaction? {
        TODO("Not yet implemented")
    }

    override suspend fun getLastBuyTransaction() = dao.getLastBuyTransaction()

    override suspend fun getLastValidTransaction() = dao.getLastValidTransaction()

    override suspend fun getLastValidTransactionForSettlement(stan: String): Transaction? =
        dao.getLastValidTransactionForSettlement(stan)

    override suspend fun getLastTransaction() = dao.getLastTransaction()

    override suspend fun updateTransaction(transaction: Transaction) = dao.updateTransaction(transaction)

    override suspend fun maskLastTransaction() {
        val transaction = dao.getLastValidTransaction()
        transaction?.let {
            if (it.card?.contains("x",true) != true){
                it.card = Utils.cardMask(it.card ?: "")
                dao.updateTransaction(transaction)
            }
        }
    }

    override suspend fun getTransactionsByDate(
        startDate: String,
        endDate: String
    ): List<Transaction>? = dao.getTransactionsByDate(startDate,endDate)

    override suspend fun getTransactionsByDateLazy(
        startDate: String,
        endDate: String,
        offset: Long
    ) = dao.getTransactionsByDateLazy(startDate,endDate,offset)

    override suspend fun getSuccessTransactions(
        startDate: String,
        endDate: String,
        processCode: String
    ) = dao.getSuccessTransactions(startDate,endDate,processCode)

    override suspend fun getSuccessTransactionsLazy(
        startDate: String,
        endDate: String,
        processCode: String,
        offset: Long
    ) = dao.getSuccessTransactionsLazy(startDate,endDate,processCode,offset)

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

    override suspend fun getLastPrintableTransaction() = dao.getLastPrintableTransaction()

    override suspend fun getPrintableTransaction(stan: String) = dao.getPrintableTransaction(stan)

    override suspend fun getSuccessCount(
        startDate: String,
        endDate: String,
        processCode: String
    ) = dao.getSuccessCount(startDate,endDate,processCode)

    override suspend fun getSuccessCountForShift(
        startDate: String,
        processCode: String,
        shift: Int
    ) = dao.getSuccessCountForShift(startDate,processCode , shift)

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
    ) = dao.getFailureCount(startDate,endDate,processCode)

    override suspend fun getFailureCountForShift(
        startDate: String,
        processCode: String,
        shift: Int
    ) = dao.getFailureCountForShift(startDate,processCode,shift)

    override suspend fun getFailureCountForUser(
        startDate: String,
        processCode: String,
        userId: Long
    ): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun getAmount(startDate: String, endDate: String, processCode: String) = dao.getAmount(startDate,endDate,processCode)

    override suspend fun getAmountForShift(
        startDate: String,
        processCode: String,
        shift: Int
    ) = dao.getAmountForShift(startDate,processCode,shift)

    override suspend fun getAmountForUser(
        startDate: String,
        endDate: String,
        processCode: String,
        userId: Long
    ): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun getCount() = dao.getCount()

    override suspend fun deleteFirtstXrows(count: Int)  = dao.deleteFirtstXrows(count)

    override suspend fun delete() = dao.delete()

    override suspend fun getStanSet(): List<Int> {

        Log.d("COUNT", dao.getCount().toString())
        if (dao.getCount()!! > 10000)
            dao.deleteFirtstXrows(5000)

        val list = mutableListOf<Int>()
        return if (dao.getLastValidTransaction() != null) {
            list.add(dao.getLastValidTransaction()!!.stan!!.toInt())
            list.add(createNewStan(dao.getLastTransaction()!!))
            list
        } else {
            list.add(0)
            if (dao.getLastTransaction() != null)
                list.add(createNewStan(dao.getLastTransaction()!!))
            else list.add(
                1
            )
            list
        }
    }

    override suspend fun updateWithUserId(userId: Long) {
        TODO("Not yet implemented")
    }

    private fun createNewStan(input: Transaction): Int {
        return if (input.stan!!.toInt() < 699999)
            input.stan!!.toInt() + 1
        else 1
    }
}