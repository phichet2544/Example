package com.example.xmlroomfirebase

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.xmlroomfirebase.databinding.ActivityMainBinding
import com.example.xmlroomfirebase.databinding.ActivityRoomBinding

class Room : AppCompatActivity() {
    private lateinit var binding: ActivityRoomBinding  //*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_room)

        binding = ActivityRoomBinding.inflate(layoutInflater)   //*
        setContentView(binding.root)                            //*
        binding.button.setText("ทดสอบ")
    }

}