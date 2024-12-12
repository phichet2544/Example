package com.example.jpapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer

import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.jpapi.API.lazy.MDdata

import com.example.jpapi.API.lazy.VMD
import com.example.jpapi.ui.theme.JpAPITheme


class MainActivity : ComponentActivity() {
    private val viewModel: VMD by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JpAPITheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .drawBehind {
                            val gradient = Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.1f)),
                                startY = 0f,
                                endY = size.height
                            )
                            drawRect(gradient, size = size)
                        },
                    color = MaterialTheme.colorScheme.background
                ) {
                    var checked by remember { mutableStateOf(false) }

//                    **1**
                    viewModel.getFilteredItems(false)
                    viewModel.fetchUsers()


                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically,) {
                            Text(
                                "โชว์เฉพาะที่ยังไม่สำเร็จ"
                            )
                            Checkbox(
                                checked = checked,
                                onCheckedChange = { checked = it }
                            )

                        }
                        lazyD(viewModel = viewModel, checked)


                    }

                }
            }
        }
    }


    @Composable
    fun lazyD(viewModel: VMD, checked: Boolean) {
        var countt: Int
        val posts by viewModel.users.collectAsState()
        val posts2 by viewModel.filteredItems.collectAsState()

        val beforeData = if (checked) { posts2 } else { posts }
        var currentPage by remember { mutableStateOf(1) }
//            .take(15)
        Column {
            Row {
                Button(onClick = { currentPage=1 }) { Text(text = "แสดงรายการทั้งหมด") }
                Button(onClick = { currentPage=2 }) { Text(text = "แสดง 10 รายการแรก") }
            }
        LazyColumn(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(if(currentPage ==1){beforeData}else{beforeData.take(10)}) { index, debt -> // ตรวจสอบให้แน่ใจว่า 'debts' เป็น List<Debt>
                // ส่ง 'debt' เข้าไปใน Composable ที่ถูกต้อง
                countt = index + 1

                OutlinedCard(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp), // เพิ่ม padding ภายใน OutlinedCard
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "  $countt ${debt.title} ",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis, // ใช้ overflow เพื่อให้แสดง "..." เมื่อข้อความยาวเกิน
                            modifier = Modifier.weight(1f) // ให้ Text ใช้พื้นที่ที่เหลือใน Row
                        )
                        Checkbox(
                            checked = debt.completed,
                            onCheckedChange = { /* handle check change here */ },
                            enabled = false
                        )
                    }
                }


            }
        }
        }

    }
}