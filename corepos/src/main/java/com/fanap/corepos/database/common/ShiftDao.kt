package com.fanap.corepos.database.common

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.fanap.corepos.database.service.model.Shift
import com.fanap.corepos.database.service.model.User

@Dao
interface ShiftDao {
    @Query("SELECT * FROM `Shift`")
    suspend fun getAllValues(): List<Shift>?

    @Query("SELECT * FROM `Shift` where name = :name")
    suspend fun getValue(name: String): Shift?

    @Query("SELECT * FROM `Shift` where user = :userId")
    suspend fun getValueByUserId(userId: Long): Shift?

    @Insert
    suspend fun insert(shift: Shift)

    @Query("SELECT * FROM `Shift` ORDER BY id DESC LIMIT 1")
    suspend fun getLastShift(): Shift?

    @Query("SELECT * FROM `Shift` where startDate>= :date and name =:name ORDER BY startDate DESC LIMIT 1")
    suspend fun getShiftByName(name: Int, date: String): Shift?

    @Query("SELECT * FROM `Shift` where startDate>= :date and user =:userId ORDER BY startDate DESC LIMIT 1")
    suspend fun getShiftByUserId(userId: Long, date: String): Shift?

    @Query("SELECT count(*) FROM `Shift`")
    suspend fun getCount(): Int?

    @Query("Delete from Shift")
    suspend fun delete()

    @Update
    suspend fun update(shift: Shift)


    /*@Query("SELECT * FROM `Shift` where  user = :userId ORDER BY startDate DESC LIMIT 1" )
 fun getUserById(userId: User) :User?*/


}


