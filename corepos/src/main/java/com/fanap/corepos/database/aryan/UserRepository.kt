package com.fanap.corepos.database.aryan

import android.content.Context
import androidx.lifecycle.LiveData
import com.fanap.corepos.database.AppDatabase
import com.fanap.corepos.database.base.IUserRepository
import com.fanap.corepos.database.common.UserDao
import com.fanap.corepos.database.service.model.User

class UserRepository(context: Context):IUserRepository {

 private var dao: UserDao = AppDatabase.getDatabase(context)?.userDao()!!

 override suspend fun getAllValues(): List<User>? =dao.getAllValues()

 override suspend fun getAllUserId(): User? =dao.getAllUserId()

 override suspend fun getValue(useId: Long): User? = dao.getValue(useId)

 override suspend fun insert(users:User) {
  users.userId?.let { dao.deleteByName(it) }
  dao.insert(users)
 }

 override suspend fun delete(users: User)= dao.delete(users)

 override suspend fun deleteByName(useId: Long) = dao.deleteByName(useId)

 override suspend fun updateUser(users: User) = dao.updateUser(users)

 override suspend fun getShiftByUserId(userId: Long, date: String): User? = dao.getShiftByUserId(userId,date)

 override suspend fun getCount() = dao.getCount()

 override suspend fun getEnabledUser(isEnabled: Boolean): User? = dao.getEnabledUser(isEnabled = true)

 override suspend fun updateStatusUserForStart(start:String ,enabled: Boolean, disabled: Boolean, userId: Long) = dao.updateStatusUserForStart(start ,enabled,disabled,userId)

 override suspend fun updateStatusUserForEnd(end: String, enabled: Boolean, disabled: Boolean, userId: Long) = dao.updateStatusUserForEnd(end,enabled,disabled,userId)

}
