package com.example.xmlroomfirebase.RoomFolder

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class dataViewModel(application: Application): AndroidViewModel(application) {
    private val yourDao = dataDatabase.getInstance(application).dataDao()

    fun insert(dataRoom: dataRoom){
        viewModelScope.launch(Dispatchers.IO){
            yourDao.insert(dataRoom)
        }
    }

    fun getAllEntities(onResult: (List<dataRoom>) -> Unit) {
        viewModelScope.launch {
            val entities = yourDao.getAll()
            onResult(entities)
        }}


}

