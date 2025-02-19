package com.fanap.corepos.database.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "Settings")
class Settings {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0

    @ColumnInfo(name = "name")
    var name: String?

    @ColumnInfo(name = "value")
    var value: String?

    constructor(name: String, value: String) {
        this.name = name
        this.value = value
    }

    @Ignore
    constructor(id: Int, name: String, value: String) {
        this.id = id
        this.name = name
        this.value = value
    }
}