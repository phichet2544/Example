package com.example.testfirebase

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testfirebase.ui.theme.TestFirebaseTheme
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import org.checkerframework.checker.units.qual.s
import kotlin.coroutines.suspendCoroutine

data class InputAll (val text:String?=null,val num:Int?=null,val boo:Boolean?=null)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestFirebaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val data = remember { mutableStateListOf<InputAll?>() }
                    // เรียกใช้ Firebase เมื่อ composable นี้ถูกเรียก
                    LaunchedEffect(Unit) {
                        val re = Firebase.database.getReference("Test")

                         //    re.addChildEventListener()
                         //    re.addValueEventListener()

                        re.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(snapshot: DataSnapshot) {
                                if (snapshot.exists()) {

                                    for (ChildrenSnapshot in snapshot.children) {
                                        val inputAll = ChildrenSnapshot.getValue(InputAll::class.java)
                                        inputAll?.let {
                                            data.add(it)  // เพิ่มข้อมูลลงใน data
                                        }
                                        Log.d("debug", inputAll?.text.toString())

                                    }

                                }
                            }

                            override fun onCancelled(error: DatabaseError) {
                                Log.d("debug", "error")
                            }
                        })
                    }

                    Column(modifier = Modifier.padding(16.dp)) {
                        data.forEach { item ->
                            item?.let {
                                Text(text = "Text: ${it.text}, Number: ${it.num}, Boolean: ${it.boo}")
                            }
                        }
                        Button(onClick = { AddDATA() }) {
                           Text(text = "เพิ่ม")
                        }
                    }

                    
                }
            }
        }
    }
}


fun AddDATA(){

//    val linkdata ="https://coviddiagnosis-aa1b5-default-rtdb.asia-southeast1.firebasedatabase.app/"
//    val database= FirebaseDatabase.getInstance(linkdata)

    val da = Firebase.database.getReference("Test").child("Part2")
    da.setValue(InputAll("test ", num = 10,true))
}

fun abountVersion(){


//    id("com.google.gms.google-services") version "4.4.2" apply false
//    id("com.google.gms.google-services")
//    implementation ("com.google.firebase:firebase-database-ktx")
//    implementation(platform("com.google.firebase:firebase-bom:33.6.0"))
//    implementation("com.google.firebase:firebase-analytics")

}

