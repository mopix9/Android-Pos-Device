package com.fanap.corepos.database.base

import android.content.Context
import com.fanap.corepos.database.service.model.Settings

interface ISettingsRepository {

    suspend fun getAllValues(): List<Settings>?

    suspend fun getValue(name: String): Settings?

    suspend fun update(setting: Settings)

    suspend fun insert(setting: Settings)

    suspend fun delete(setting: Settings)

    suspend fun deleteByName(name: String)
}