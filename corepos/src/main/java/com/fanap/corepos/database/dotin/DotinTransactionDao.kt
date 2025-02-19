package com.fanap.corepos.database.dotin

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fanap.corepos.database.service.model.Transaction

@Dao
interface DotinTransactionDao {

    @Query("SELECT * FROM `Transaction`")
    suspend fun getAll(): List<Transaction>?

    @Query("SELECT * FROM `Transaction` where mti in('20','22','24','3C','2C') ORDER BY id DESC LIMIT 1")
    suspend fun getLastBuyTransaction(): Transaction?

    @Insert
    suspend fun insert(transaction: Transaction?): Long

    @Query("SELECT * FROM `Transaction` where mti in('20','22','24','3C','2C','26') and status in ('AdviceSentTimeOut','AdviceResDidNotUnpack','AdviceResUnpacked','AdviceResReceived' , 'AdviceSent','ReceiptPrinted','ReceiptPrinted','StartSuccessPrint')  ORDER BY id DESC LIMIT 1")
    suspend fun getLastValidTransaction(): Transaction?

    @Query("SELECT * FROM `Transaction` ORDER BY id DESC LIMIT 1")
    suspend fun getLastTransaction(): Transaction?

    @Query("UPDATE `Transaction` SET status = :status , rrn = :rrn , response = :responseCode , date = :dateTime WHERE id = :id")
    suspend fun updateTransaction(
        id: Long,
        status: String?,
        rrn: String?,
        responseCode: String?,
        dateTime: String?
    ): Int

    @Query("SELECT * FROM `Transaction` WHERE id = :id")
    suspend fun getTransaction(id: Long): Transaction?

    @Query("UPDATE `Transaction` SET description = :description  WHERE id = :id")
    suspend fun updateTransaction(id: Long, description: String?): Int

    @Query("UPDATE `Transaction` SET timeStamp = :timeStamp  WHERE id = :id")
    suspend fun updateTimeStamp(id: Long, timeStamp: String?): Int

    @Query("SELECT * FROM `Transaction` where date >= :startDate and date<= :endDate and response is not null and response!='' and (mti = '20' or mti = '22' or mti = '24' or mti = '3C') ORDER BY id DESC")
    suspend fun getTransactionsByDate(startDate: String?, endDate: String?): List<Transaction?>?

    @Query("SELECT * FROM `Transaction` where date >= :startDate and date<= :endDate and response is not null and response!='' and (mti = '20' or mti = '22' or mti = '24' or mti = '3C') ORDER BY id DESC LIMIT 10 OFFSET :offset")
    suspend fun getTransactionsByDateLazy(
        startDate: String?,
        endDate: String?,
        offset: Long?
    ): List<Transaction?>?

    @Query("SELECT * FROM `Transaction` where date >= :startDate and date<= :endDate and response = '00' and  mti = :mti and status in('AdviceSentTimeOut','AdviceResDidNotUnpack','AdviceResUnpacked','AdviceResReceived' , 'AdviceSent','ReceiptPrinted','StartSuccessPrint') ORDER BY id DESC")
    suspend fun getSuccessTransactions(
        startDate: String?,
        endDate: String?,
        mti: String?
    ): List<Transaction?>?

    @Query("SELECT * FROM `Transaction` where date >= :startDate and date<= :endDate and response = '00' and  mti = :mti and status in('AdviceSentTimeOut','AdviceResDidNotUnpack','AdviceResUnpacked','AdviceResReceived' , 'AdviceSent','ReceiptPrinted','StartSuccessPrint') ORDER BY id DESC LIMIT 1 OFFSET :offset")
    suspend fun getSuccessTransactionsLazy(
        startDate: String?,
        endDate: String?,
        mti: String?,
        offset: Long?
    ): List<Transaction?>?

    @Query("SELECT * FROM `Transaction` where response is not null and response!='' and (mti = '20' or mti = '22' or mti = '24' or mti = '3C') ORDER BY id DESC LIMIT 1")
    suspend fun getLastPrintableTransaction(): Transaction?

    @Query("SELECT * FROM `Transaction` where response is not null and response!='' and stan = :stan and (mti = '20' or mti = '22' or mti = '24' or mti = '3C') ORDER BY date DESC LIMIT 1")
    suspend fun getPrintableTransaction(stan: String?): Transaction?

    @Query("SELECT count(*) FROM `Transaction` where date >= :startDate and date<= :endDate and  mti = :mti and response is not null and response = '00' and status in('AdviceSentTimeOut','AdviceResDidNotUnpack','AdviceResUnpacked','AdviceResReceived' , 'AdviceSent','ReceiptPrinted','StartSuccessPrint')")
    suspend fun getSuccessCount(startDate: String?, endDate: String?, mti: String?): Int?

    @Query("SELECT count(*) FROM `Transaction` where date >= :startDate and  mti = :mti and shift = :shift and response is not null and response = '00' and status in('AdviceSentTimeOut','AdviceResDidNotUnpack','AdviceResUnpacked','AdviceResReceived' , 'AdviceSent','ReceiptPrinted','StartSuccessPrint')")
    suspend fun getSuccessCountForShift(startDate: String?, mti: String?, shift: Int): Int?

    @Query("SELECT count(*) FROM `Transaction` where (date >= :startDate and date<= :endDate and  mti = :mti and response is not null and response!= '00') or (date >= :startDate and date<= :endDate and  mti = :mti and response is not null and response = '00' and status in ('ReverseSent','ReverseResReceived','ReverseResUnpacked','ReverseResDidNotUnpack','ReverseSentTimeOut'))")
    suspend fun getFailureCount(startDate: String?, endDate: String?, mti: String?): Int?

    @Query("SELECT count(*) FROM `Transaction` where (date >= :startDate and  mti = :mti and shift = :shift and response is not null and response!= '00') or (date >= :startDate and  mti = :mti and shift = :shift and response is not null and response = '00' and status in ('ReverseSent','ReverseResReceived','ReverseResUnpacked','ReverseResDidNotUnpack','ReverseSentTimeOut'))")
    suspend fun getFailureCountForShift(startDate: String?, mti: String?, shift: Int): Int?

    @Query("SELECT COALESCE(sum(COALESCE(cast(amount as INTEGER),0)), 0) FROM `Transaction` where date >= :startDate and date<= :endDate and  mti = :mti and response is not null and response= '00' and amount is not null and amount!= '' and cast(amount as INTEGER)>0 and status in('AdviceSentTimeOut','AdviceResDidNotUnpack','AdviceResUnpacked','AdviceResReceived' , 'AdviceSent','ReceiptPrinted','StartSuccessPrint')")
    suspend fun getAmount(startDate: String?, endDate: String?, mti: String?): Int?

    @Query("SELECT COALESCE(sum(COALESCE(cast(amount as INTEGER),0)), 0) FROM `Transaction` where date >= :startDate and  mti = :mti and shift = :shift and response is not null and response= '00' and amount is not null and amount!= '' and cast(amount as INTEGER)>0 and status in('AdviceSentTimeOut','AdviceResDidNotUnpack','AdviceResUnpacked','AdviceResReceived' , 'AdviceSent','ReceiptPrinted','StartSuccessPrint')")
    suspend fun getAmountForShift(startDate: String?, mti: String?, shift: Int): Int?

    @Query("SELECT count(*) FROM `Transaction`")
    suspend fun getCount(): Int?

    @Query("Delete from `Transaction` where rowid IN (Select rowid from `Transaction` limit :count)")
    suspend fun deleteFirtstXrows(count: Int)

    @Query("Delete from `Transaction`")
    suspend fun delete()

}