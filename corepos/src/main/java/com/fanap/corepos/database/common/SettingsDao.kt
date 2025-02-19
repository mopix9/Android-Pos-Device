package com.fanap.corepos.database.common

import androidx.room.*
import com.fanap.corepos.database.service.model.Settings

@Dao
interface SettingsDao {

    @Query("SELECT * FROM `Settings`")
    suspend fun getAllValues(): List<Settings>?

    @Query("SELECT * FROM `Settings` where name = :name")
    suspend fun getValue(name: String): Settings?

    @Update
    suspend fun update(setting: Settings)

    @Insert
    suspend fun insert(setting: Settings)

    @Delete
    suspend fun delete(setting: Settings)

    @Query("delete FROM `Settings` where name = :name")
    suspend fun deleteByName(name: String)
}