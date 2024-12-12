package com.example.xmlroomfirebase.RoomFolder

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class converter
{
    @TypeConverter
    fun fromArray(array: Array<String>): String {
        return Gson().toJson(array)
    }

    @TypeConverter
    fun toArray(json: String): Array<String> {
        val type = object : TypeToken<Array<String>>() {}.type
        return Gson().fromJson(json, type)
    }
}