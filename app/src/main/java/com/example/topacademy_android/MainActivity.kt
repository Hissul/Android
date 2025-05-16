package com.example.topacademy_android

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.topacademy_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener{
            val intent = Intent(this, SecondActivity::class.java)

            val name = binding.nameField.text.toString()
            if(name.isNotEmpty()){
                intent.putExtra("KEY_NAME", name)
            }
            startActivity(intent)
        }
    }


}