package com.fanap.corepos.database.base

import com.fanap.corepos.database.service.model.Shift

interface IShiftRepository {

 suspend fun getAllValue():List<Shift>?

 suspend fun getValue(name: String): Shift?

 suspend fun getValueByUserId(userId: Long): Shift?

 suspend fun getShiftByUserId(userId: Long, date: String): Shift?

 suspend fun insert(shift: Shift)

 suspend fun getLastShift(): Shift?

 suspend fun getShiftByName(name: Int, date: String): Shift?

 suspend fun getCount(): Int?

 suspend fun delete()

 suspend fun update(shift: Shift)
}
