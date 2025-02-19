package com.fanap.corepos.database.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PosLog")
class PosLog(
    @field:ColumnInfo(name = "date") var date: String?,
    @field:ColumnInfo(name = "description") var description: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id = 0

}
