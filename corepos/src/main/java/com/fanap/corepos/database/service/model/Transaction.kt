package com.fanap.corepos.database.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Transaction")
class Transaction() {
    @PrimaryKey(autoGenerate = true) var id: Long = 0

    @ColumnInfo(name = "stan") var stan: String? = null
    @ColumnInfo(name = "date") var date: String? = null
    @ColumnInfo(name = "amount") var amount: String? = null
    @ColumnInfo(name = "rrn") var rrn: String? = null
    @ColumnInfo(name = "mti") var mti: String? = null
    @ColumnInfo(name = "status") var status: String? = null
    @ColumnInfo(name = "response") var response: String? = null
    @ColumnInfo(name = "card") var card: String? = null
    @ColumnInfo(name = "description") var description: String? = null
    @ColumnInfo(name = "description2") var description2: String? = null
    @ColumnInfo(name = "description3") var description3: String? = null
    @ColumnInfo(name = "process_code") var processCode: String? = null
    @ColumnInfo(name = "timeStamp") var timeStamp: String? = null
    @ColumnInfo(name = "shift") var shift: Int?  = null
    @ColumnInfo(name = "user") var userId: Long?  = null



}
