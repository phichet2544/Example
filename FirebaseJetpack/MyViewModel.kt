package com.example.testfirebase

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MyViewModel : ViewModel() {
    private val database: DatabaseReference = Firebase.database.getReference("Test")

    private val _users = MutableStateFlow<List<InputAll>>(emptyList())
    val users: StateFlow<List<InputAll>> = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        database.get().addOnSuccessListener { snapshot ->
            val userList = mutableListOf<InputAll>()
            for (child in snapshot.children) {
                val user = child.getValue(InputAll::class.java)
                user?.let { userList.add(it) }
            }
            _users.value = userList
        }.addOnFailureListener { exception ->
            // Handle the error
        }
    }
}

@Composable
fun UIlist(){
//        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    val viewModel: MyViewModel = viewModel()
    val users by viewModel.users.collectAsState()

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        itemsIndexed(users) { index,data ->
            Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Name: ${data.text}+${data.num}+${data.boo}", style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }


}