package com.fanap.corepos.database.common

import androidx.lifecycle.LiveData
import androidx.room.*
import com.fanap.corepos.database.service.model.Shift
import com.fanap.corepos.database.service.model.User
import com.justtide.service.dev.R.id.update

@Dao
interface UserDao {

 @Query("SELECT * FROM `Users`")
 suspend fun getAllValues(): List<User>?

 @Query("SELECT * FROM `Users`")
 suspend fun getAllUserId() : User?

 @Query("SELECT * FROM `Users` where userId = :useId")
 suspend fun getValue(useId: Long ): User?


 @Insert
 suspend fun insert(users:User)

 @Delete
 suspend fun delete(users: User)

 @Query("delete FROM `Users` where userId = :useId")
 suspend fun deleteByName(useId: Long)

 @Update
suspend fun updateUser(users:User)

 @Query("SELECT * FROM `Users` where startDate>= :date and userId =:userId  ORDER BY startDate DESC LIMIT 1")
 suspend fun getShiftByUserId(userId:Long, date: String): User?

 @Query("SELECT * FROM  `Users` where enabled =:isEnabled ")
suspend fun getEnabledUser(isEnabled: Boolean = true): User?

 @Query("SELECT count(*) FROM `Users`")
 suspend fun getCount(): Int?

 @Query("update `Users` set  startDate = :start,enabled = :enabled , disabled  = :disabled where userId = :userId   ")
 suspend fun updateStatusUserForStart(start:String , enabled: Boolean, disabled : Boolean, userId:Long)

 @Query("update `Users` set  endDate = :end,enabled = :enabled , disabled  = :disabled where userId = :userId   ")
 suspend fun updateStatusUserForEnd(end:String , enabled: Boolean, disabled : Boolean, userId:Long)

}