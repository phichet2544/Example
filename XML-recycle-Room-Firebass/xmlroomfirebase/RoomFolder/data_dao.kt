package com.example.xmlroomfirebase.RoomFolder

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface data_dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: dataRoom)

    @Query("SELECT * FROM data_db")
    suspend fun getAll(): List<dataRoom>
}