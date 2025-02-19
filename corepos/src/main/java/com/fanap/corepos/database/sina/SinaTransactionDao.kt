package com.fanap.corepos.database.sina

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fanap.corepos.database.service.model.Transaction

@Dao
interface SinaTransactionDao {

    @Query("SELECT * FROM `Transaction`")
    suspend fun getAll(): List<Transaction>?

    @Query("SELECT * FROM `Transaction` where mti = '0200' and process_code in ('000000','190000','170000','100000') ORDER BY id DESC LIMIT 1")
    suspend fun getLastBuyTransaction(): Transaction?

    @Insert
    suspend fun insert(transaction: Transaction): Long

    @Query("SELECT * FROM `Transaction` where mti = '0200' and response = '00' and status in ('AdviceResUnpacked','ReceiptPrinted','StartSuccessPrint')  ORDER BY id DESC LIMIT 1")
    suspend fun getLastValidTransaction(): Transaction?

    @Query("SELECT * FROM `Transaction` where mti = '0200' and response = '00' and status in ('AdviceResUnpacked','ReceiptPrinted','StartSuccessPrint') and stan != :stan  ORDER BY id DESC LIMIT 1")
    suspend fun getLastValidTransactionForSettlement(stan : String): Transaction?

    @Query("SELECT * FROM `Transaction` ORDER BY id DESC LIMIT 1")
    suspend fun getLastTransaction(): Transaction?

    @Update
    suspend fun updateTransaction(transaction: Transaction): Int

    @Query("SELECT * FROM `Transaction` WHERE id = :id")
    suspend fun getTransaction(id: Long): Transaction?

    @Query("SELECT * FROM `Transaction` where date >= :startDate and date<= :endDate and response is not null and response!='' and mti = '0200' and process_code != '310000' ORDER BY id DESC")
    suspend fun getTransactionsByDate(startDate: String, endDate: String): List<Transaction>?

    @Query("SELECT * FROM `Transaction` where date >= :startDate and date<= :endDate and response is not null and response!='' and mti = '0200' and process_code != '310000' ORDER BY id DESC LIMIT 10 OFFSET :offset")
    suspend fun getTransactionsByDateLazy(
        startDate: String,
        endDate: String,
        offset: Long
    ): List<Transaction>?

    @Query("SELECT * FROM `Transaction` where date >= :startDate and date<= :endDate and response = '00' and mti not in ('0220','0400') and  process_code = :processCode and status in('AdviceSentTimeOut','AdviceResDidNotUnpack','AdviceResUnpacked','AdviceResReceived' , 'AdviceSent','ReceiptPrinted','StartSuccessPrint') ORDER BY id DESC")
    suspend fun getSuccessTransactions(
        startDate: String,
        endDate: String,
        processCode: String
    ): List<Transaction>?

    @Query("SELECT * FROM `Transaction` where date >= :startDate and date<= :endDate and response = '00'and mti not in ('0220','0400') and  process_code = :processCode and status in('AdviceSentTimeOut','AdviceResDidNotUnpack','AdviceResUnpacked','AdviceResReceived' , 'AdviceSent','ReceiptPrinted','StartSuccessPrint') ORDER BY id DESC LIMIT 10 OFFSET :offset")
    suspend fun getSuccessTransactionsLazy(
        startDate: String,
        endDate: String,
        processCode: String,
        offset: Long
    ): List<Transaction>?

    @Query("SELECT * FROM `Transaction` where response is not null and response!='' and  process_code in ('000000','190000', '180000' ,'170000','100000') and mti not in ('0220','0400') ORDER BY id DESC LIMIT 1")
    suspend fun getLastPrintableTransaction(): Transaction?

    @Query("SELECT * FROM `Transaction` where response is not null and response!='' and stan = :stan and process_code in ('000000','190000', '180000' ,'170000','100000') and mti not in ('0220','0400') ORDER BY date DESC LIMIT 1")
    suspend fun getPrintableTransaction(stan: String): Transaction?

    @Query("SELECT count(*) FROM `Transaction` where date >= :startDate and date<= :endDate and  process_code = :processCode and mti not in ('0220','0400') and response is not null and response = '00' and status in('AdviceSentTimeOut','AdviceResDidNotUnpack','AdviceResUnpacked','AdviceResReceived' , 'AdviceSent','ReceiptPrinted','StartSuccessPrint')")
    suspend fun getSuccessCount(startDate: String, endDate: String, processCode: String): Int?

    @Query("SELECT count(*) FROM `Transaction` where date >= :startDate and  process_code = :processCode and shift = :shift and response is not null and response = '00' and status in('AdviceSentTimeOut','AdviceResDidNotUnpack','AdviceResUnpacked','AdviceResReceived' , 'AdviceSent','ReceiptPrinted','StartSuccessPrint')")
    suspend fun getSuccessCountForShift(startDate: String, processCode: String, shift: Int): Int?

    @Query("SELECT count(*) FROM `Transaction` where date >= :startDate and date<= :endDate and  process_code = :processCode and mti not in ('0220','0400') and response is not null and response!= '00'")
    suspend fun getFailureCount(startDate: String, endDate: String, processCode: String): Int?

    @Query("SELECT count(*) FROM `Transaction` where (date >= :startDate and  process_code = :processCode and shift = :shift and response is not null and response!= '00') or (date >= :startDate and  process_code = :processCode and shift = :shift and response is not null and response = '00' and status in ('ReverseSent','ReverseResReceived','ReverseResUnpacked','ReverseResDidNotUnpack','ReverseSentTimeOut'))")
    suspend fun getFailureCountForShift(startDate: String, processCode: String, shift: Int): Int?

    @Query("SELECT COALESCE(sum(COALESCE(cast(amount as INTEGER),0)), 0) FROM `Transaction` where date >= :startDate and date<= :endDate and  process_code = :processCode and response is not null and response= '00' and amount is not null and amount!= '' and cast(amount as INTEGER)>0 and status in('AdviceSentTimeOut','AdviceResDidNotUnpack','AdviceResUnpacked','AdviceResReceived' , 'AdviceSent','ReceiptPrinted','StartSuccessPrint')")
    suspend fun getAmount(startDate: String, endDate: String, processCode: String): Int?

    @Query("SELECT COALESCE(sum(COALESCE(cast(amount as INTEGER),0)), 0) FROM `Transaction` where date >= :startDate and  process_code = :processCode and shift = :shift and response is not null and response= '00' and amount is not null and amount!= '' and cast(amount as INTEGER)>0 and status in('AdviceSentTimeOut','AdviceResDidNotUnpack','AdviceResUnpacked','AdviceResReceived' , 'AdviceSent','ReceiptPrinted','StartSuccessPrint')")
    suspend fun getAmountForShift(startDate: String, processCode: String, shift: Int): Int?

    @Query("SELECT count(*) FROM `Transaction`")
    suspend fun getCount(): Int?

    @Query("Delete from `Transaction` where rowid IN (Select rowid from `Transaction` limit :count)")
    suspend fun deleteFirtstXrows(count: Int)

    @Query("Delete from `Transaction`")
    suspend fun delete()

}