package com.example.xmlroomfirebase

import ItemAdapter
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.xmlroomfirebase.Recyclcview.item_re
import com.example.xmlroomfirebase.RoomFolder.dataRoom
import com.example.xmlroomfirebase.RoomFolder.dataViewModel
import com.example.xmlroomfirebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: dataViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    var myList = mutableListOf<item_re>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button2.setOnClickListener {
            viewModel.insert(dataRoom(0,"test",arrayOf("Tag1", "Tag2")))
        }

        binding.buttonmain.setOnClickListener {
            val intent = Intent(this,Room::class.java)
            startActivity(intent)
        }

        val items = listOf(
                item_re("Title 1"),
            item_re("Title 2"),
            item_re("Title 3")
        )

        viewModel.getAllEntities {entities->
            entities.forEach {

                myList.add(item_re(it.name,it.tags.joinToString(", ")))
                println("ID: ${it.id}, Name: ${it.name}, Tags: ${it.tags.joinToString(", ")}")
            }
            binding.recycleview2.adapter = ItemAdapter(myList)
            binding.recycleview2.layoutManager = LinearLayoutManager(this)
        }
        // ตั้งค่า Adapter และ LayoutManager
        binding.idRecycle.adapter = ItemAdapter(items)
        binding.idRecycle.layoutManager = LinearLayoutManager(this)





    }
}