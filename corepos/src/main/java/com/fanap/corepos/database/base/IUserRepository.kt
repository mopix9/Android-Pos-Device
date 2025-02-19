package com.fanap.corepos.database.base

import androidx.lifecycle.LiveData
import com.fanap.corepos.database.service.model.User
import java.io.Serializable

interface IUserRepository {
 suspend fun getAllValues(): List<User>?
 suspend fun getAllUserId() : User?

  suspend fun getValue(useId: Long): User?
 suspend fun insert(users: User)
 suspend fun delete(users: User)
 suspend fun deleteByName(useId: Long)
 suspend fun updateUser(users:User)
 suspend fun getShiftByUserId(userId: Long, date: String): User?
 suspend fun getCount(): Int?
 suspend fun getEnabledUser(isEnabled: Boolean = true):User?
 suspend fun updateStatusUserForStart(start:String ,enabled: Boolean, disabled: Boolean, userId:Long )
 suspend fun updateStatusUserForEnd(end:String , enabled: Boolean, disabled : Boolean, userId:Long)
}