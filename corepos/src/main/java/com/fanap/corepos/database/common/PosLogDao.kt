package com.fanap.corepos.database.common

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.fanap.corepos.database.service.model.PosLog

@Dao
interface PosLogDao {
    @Query("SELECT * FROM `PosLog`")
    suspend fun getAll(): List<PosLog>?

    @Query("SELECT count(*) FROM `PosLog`")
    suspend fun getCount(): Int?

    @Insert
    suspend fun insert(posLog: PosLog)

    @Query("Delete from poslog")
    suspend fun delete()
}
