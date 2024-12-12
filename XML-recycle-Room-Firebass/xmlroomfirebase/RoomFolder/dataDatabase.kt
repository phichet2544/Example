package com.example.xmlroomfirebase.RoomFolder

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [dataRoom::class], version = 1)
@TypeConverters(converter::class)
abstract class dataDatabase : RoomDatabase() {
    abstract fun dataDao(): data_dao

    companion object {
        @Volatile
        private var INSTANCE: dataDatabase? = null

        fun getInstance(context: Context): dataDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    dataDatabase::class.java,
                    "dataDatabase"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}