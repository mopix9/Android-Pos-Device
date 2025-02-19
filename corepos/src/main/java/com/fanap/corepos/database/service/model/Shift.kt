package com.fanap.corepos.database.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Shift")
class Shift(
 @field:ColumnInfo(name = "startDate") var startDate: String?,
 @field:ColumnInfo(name = "endDate") var endDate: String?,
 @field:ColumnInfo(name = "name") var name: Int,
 @field:ColumnInfo(name = "finished") var isFinished: Boolean,
 @field:ColumnInfo(name = "enabled") var isEnabled: Boolean,
 @field:ColumnInfo(name = "user") var user: Long,
 @PrimaryKey(autoGenerate = true)
 var id: Long = 0
)
