package com.fanap.corepos.database.common

import android.content.Context
import com.fanap.corepos.database.AppDatabase
import com.fanap.corepos.database.base.ISettingsRepository
import com.fanap.corepos.database.service.model.Settings

class SettingsRepository(context: Context) : ISettingsRepository {

    private var dao: SettingsDao = AppDatabase.getDatabase(context)?.settingsDao()!!

    override suspend fun getAllValues() = dao.getAllValues()

    override suspend fun getValue(name: String) = dao.getValue(name)

    override suspend fun update(setting: Settings) = dao.update(setting)

    override suspend fun insert(setting: Settings) {
        dao.deleteByName(setting.name!!)
        dao.insert(setting)
    }
    override suspend fun delete(setting: Settings) = dao.delete(setting)

    override suspend fun deleteByName(name: String) = dao.deleteByName(name)

}