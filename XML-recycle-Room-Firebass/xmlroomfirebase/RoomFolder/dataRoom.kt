package com.example.xmlroomfirebase.RoomFolder

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_db")
data class dataRoom (
    @PrimaryKey(autoGenerate = true)val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "tage") val tags: Array<String> // ใช้ Array
)