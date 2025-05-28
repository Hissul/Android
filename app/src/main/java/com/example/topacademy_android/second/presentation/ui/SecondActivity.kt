package com.example.topacademy_android.second.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import com.example.topacademy_android.R
import com.example.topacademy_android.car.presentation.ui.ListActivity
import com.example.topacademy_android.databinding.ActivitySecondBinding
import com.example.topacademy_android.second.presentation.event.SecondUiEvent
import com.example.topacademy_android.second.presentation.view_model.SecondViewModel
import com.example.topacademy_android.weather.presentation.ui.WeatherActivity
import com.example.topacademy_android.сalculator.presentation.ui.CalculatorActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private val viewModel: SecondViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nameView.text = intent.getStringExtra("KEY_NAME")

        setupToolbar()
        observeUiEvents()
        setupListeners()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val arrow = AppCompatResources.getDrawable(this, androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        arrow?.setTint(ContextCompat.getColor(this, R.color.toolbar_icon_color))
        supportActionBar?.setHomeAsUpIndicator(arrow)
    }

    private fun setupListeners() {
        binding.weatherButton.setOnClickListener {
            viewModel.onWeatherClicked()
        }

        binding.calculatorButton.setOnClickListener {
            viewModel.onCalculatorClicked()
        }

        binding.listButton.setOnClickListener {
            viewModel.onListClicked()
        }
    }

    private fun observeUiEvents() {
        viewModel.uiEvent.observe(this) { event ->
            when (event) {
                SecondUiEvent.NavigateToWeather -> {
                    startActivity(Intent(this, WeatherActivity::class.java))
                }
                SecondUiEvent.NavigateToCalculator -> {
                    startActivity(Intent(this, CalculatorActivity::class.java))
                }
                SecondUiEvent.NavigateToCarList -> {
                    startActivity(Intent(this, ListActivity::class.java))
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish(); // Возврат на предыдущую Activity
            return true;
        }
        return super.onOptionsItemSelected(item)
    }

}