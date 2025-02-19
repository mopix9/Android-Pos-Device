package com.fanap.corepos.database.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class User(
 @ColumnInfo(name = "userName")
 var name:String? = "",
 var userId: Long?
,
 @field:ColumnInfo(name = "startDate") var startDate: String?,
 @field:ColumnInfo(name = "endDate") var endDate: String?,
 @field:ColumnInfo(name = "enabled") var isEnabled: Boolean,
 @field:ColumnInfo(name = "disabled") var isDisabled: Boolean,
 @PrimaryKey(autoGenerate = true)
 var id:Long = 0
)


