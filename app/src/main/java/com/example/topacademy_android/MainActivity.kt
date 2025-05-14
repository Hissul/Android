package com.example.topacademy_android

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.topacademy_android.databinding.ActivityMainBinding
import java.sql.SQLXML

class MainActivity : AppCompatActivity() {

    companion object{
        private const val ON_CREATE = "ON_CREATE";
        private const val ON_START = "ON_START";
        private const val ON_RESUME = "ON_RESUME";
        private const val ON_PAUSE = "ON_PAUSE";
        private const val ON_STOP = "ON_STOP";
        private const val ON_RESTART = "ON_RESTART";
        private const val ON_DESTROY = "ON_DESTROY";
    }

    private lateinit var binding: ActivityMainBinding

    private var createTime : Long = 0;
    private var startTime : Long = 0;
    private var resumeTime : Long = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createTime = System.currentTimeMillis();

        Log.i(ON_CREATE, getString(R.string.on_create));
        Log.i(ON_CREATE, "Старт в $createTime мсек");
    }

    override fun onStart() {
        super.onStart()

        startTime= System.currentTimeMillis();

        Log.i(ON_START, getString(R.string.on_start));
        Log.i(ON_START, "Старт в $startTime мсек");
        Log.i(ON_START, "Разница = " + (startTime - createTime).toString());
    }

    override fun onResume() {
        super.onResume()

        resumeTime = System.currentTimeMillis();

        Log.i(ON_RESUME, getString(R.string.on_resume));
        Log.i(ON_START, "Старт в $resumeTime мсек");
        Log.i(ON_START, "Разница = " + (resumeTime - startTime).toString());
    }

    override fun onPause() {
        super.onPause()

        Log.i(ON_PAUSE, getString(R.string.on_pause));
    }

    override fun onStop() {
        super.onStop()

        Log.i(ON_STOP, getString(R.string.on_stop));
    }

    override fun onRestart() {
        super.onRestart()

        Log.i(ON_RESTART, getString(R.string.on_restart));
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.i(ON_DESTROY, getString(R.string.on_destroy));
    }
}