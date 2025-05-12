package com.example.topacademy_android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.topacademy_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object{
        private const val ON_CREATE = "ON_CREATE"
        private const val ON_START = "ON_START"
        private const val ON_RESUME ="ON_RESUME"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.i(ON_CREATE, R.string.on_create.toString())
    }

    override fun onStart() {
        super.onStart()


        Log.i(ON_START, R.string.on_start.toString())
    }

    override fun onResume() {
        super.onResume()

        Log.i(ON_RESUME, R.string.on_resume.toString())
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}