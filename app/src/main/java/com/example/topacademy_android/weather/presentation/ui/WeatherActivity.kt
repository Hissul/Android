package com.example.topacademy_android.weather.presentation.ui

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topacademy_android.weather.domain.adapter.WeatherAdapter
import com.example.topacademy_android.databinding.ActivityWeatherBinding
import androidx.lifecycle.lifecycleScope
import com.example.topacademy_android.R
import com.example.topacademy_android.weather.presentation.viewmodel.WeatherViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class WeatherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherBinding
    private val viewModel: WeatherViewModel by viewModel() // ← Koin ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        observeWeather()
        viewModel.fetchWeather(23.09, 113.17)
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val arrowDrawable = AppCompatResources.getDrawable(this, R.drawable.ic_blue_arrow)
        arrowDrawable?.setTint(ContextCompat.getColor(this, R.color.toolbar_icon_color))
        supportActionBar?.setHomeAsUpIndicator(arrowDrawable)
    }

    private fun observeWeather() {
        lifecycleScope.launch {
            viewModel.weatherList.collect { data ->
                val adapter = WeatherAdapter(data) { selectedItem ->
                    Toast.makeText(
                        this@WeatherActivity,
                        "Вы выбрали дату : ${selectedItem.date}, " +
                                "Температура : ${selectedItem.temp2m.min} – ${selectedItem.temp2m.max} °C",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                binding.weatherRecyclerView.layoutManager = LinearLayoutManager(this@WeatherActivity)
                binding.weatherRecyclerView.adapter = adapter
            }
        }

        lifecycleScope.launch {
            viewModel.error.collect { message ->
                message?.let {
                    Toast.makeText(this@WeatherActivity, "Ошибка: $it", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}