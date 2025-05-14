package com.example.topacademy_android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.topacademy_android.databinding.ActivityMainBinding
import java.sql.SQLXML

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var createTime : Long = 0;
    private var startTime : Long = 0;
    private var resumeTime : Long = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createTime = System.currentTimeMillis();

        Log.i(getString(R.string.ON_CREATE), getString(R.string.on_create));
        Log.i(getString(R.string.ON_CREATE), "Старт в $createTime мсек");
    }

    override fun onStart() {
        super.onStart()

        startTime= System.currentTimeMillis();

        Log.i(getString(R.string.ON_START), getString(R.string.on_start));
        Log.i(getString(R.string.ON_START), "Старт в $startTime мсек");
        Log.i(getString(R.string.ON_START), "Разница = " + (startTime - createTime).toString());
    }

    override fun onResume() {
        super.onResume()

        resumeTime = System.currentTimeMillis();

        Log.i(getString(R.string.ON_RESUME), getString(R.string.on_resume));
        Log.i(getString(R.string.ON_RESUME), "Старт в $resumeTime мсек");
        Log.i(getString(R.string.ON_RESUME), "Разница = " + (resumeTime - startTime).toString());
    }

    override fun onPause() {
        super.onPause()

        Log.i(getString(R.string.ON_PAUSE), getString(R.string.on_pause));
    }

    override fun onStop() {
        super.onStop()

        Log.i(getString(R.string.ON_STOP), getString(R.string.on_stop));
    }

    override fun onRestart() {
        super.onRestart()

        Log.i(getString(R.string.ON_RESTART), getString(R.string.on_restart));
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.i(getString(R.string.ON_DESTROY), getString(R.string.on_destroy));
    }
}