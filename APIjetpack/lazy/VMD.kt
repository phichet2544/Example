package com.example.jpapi.API.lazy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VMD: ViewModel() {
    private val _users = MutableStateFlow<List<MDdata>>(emptyList())
    val users: StateFlow<List<MDdata>> = _users

    private val _filteredItems = MutableStateFlow<List<MDdata>>(emptyList())
    val filteredItems: StateFlow<List<MDdata>> = _filteredItems

//กำหนดในนี่้เลยหรือกำหนดหน้าการทำงาน  **1**
//    init {
//        fetchUsers()
//        getFilteredItems(true)
//    }

     fun fetchUsers() {
        viewModelScope.launch {
            try {
                val userList = lazyBuild.api.getPosts()
                _users.value = userList
            } catch (e: Exception) {
                // Handle exception
            }
        }
    }

    fun getFilteredItems(isActive: Boolean) {
        viewModelScope.launch {
            try {
                val response = lazyBuild.api.getFilteredItems(isActive)
                _filteredItems.value = response
            } catch (e: Exception) {
                // จัดการ error
            }
        }
    }



}