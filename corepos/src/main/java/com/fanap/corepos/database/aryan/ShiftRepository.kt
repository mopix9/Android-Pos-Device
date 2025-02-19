package com.fanap.corepos.database.aryan

import android.content.Context
import com.fanap.corepos.database.AppDatabase
import com.fanap.corepos.database.base.IShiftRepository
import com.fanap.corepos.database.common.SettingsDao
import com.fanap.corepos.database.common.ShiftDao
import com.fanap.corepos.database.service.model.Shift

class ShiftRepository(context: Context):IShiftRepository {
 private var dao: ShiftDao = AppDatabase.getDatabase(context)?.shiftDao()!!


 override suspend fun getAllValue()=dao.getAllValues()

 override suspend fun getValue(name: String) = dao.getValue(name)

 override suspend fun getValueByUserId(userId: Long) = dao.getValueByUserId(userId)

 override suspend fun getShiftByUserId(userId: Long, date: String) = dao.getShiftByUserId(userId,date)

 override suspend fun insert(shift: Shift) = dao.insert(shift)

 override suspend fun getLastShift()= dao.getLastShift()

 override suspend fun getShiftByName(name: Int, date: String) = dao.getShiftByName(name,date)

 override suspend fun getCount() = dao.getCount()

 override suspend fun delete() = dao.delete()

 override suspend fun update(shift: Shift) = dao.update(shift)
}