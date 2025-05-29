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
import com.example.topacademy_android.weather.data.remote.api.RetrofitClient
import androidx.lifecycle.lifecycleScope
import com.example.topacademy_android.R
import kotlinx.coroutines.launch


class WeatherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolBar = binding.toolBar
        setSupportActionBar(toolBar)

        // Включаем стрелку "назад"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // меняем цвет стрелки
        val arrowDrawable = AppCompatResources.getDrawable(
            this, R.drawable.ic_blue_arrow)

        arrowDrawable?.setTint(ContextCompat.getColor(this, R.color.toolbar_icon_color))
        supportActionBar?.setHomeAsUpIndicator(arrowDrawable)

        // Гуанчжоу, Китай
        fetchWeather(latitude = 23.09, longitude = 113.17)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish() // Возврат на предыдущую Activity
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun fetchWeather(latitude: Double, longitude: Double) {

        lifecycleScope.launch {
            try {
                val response = RetrofitClient.weatherApi.getWeatherForecast(
                    longitude = longitude,
                    latitude = latitude
                )

                val data = response.dataseries

                binding.weatherRecyclerView.layoutManager =
                    LinearLayoutManager(this@WeatherActivity)

                val adapter = WeatherAdapter(data) { selectedItem ->
                    Toast.makeText(
                        this@WeatherActivity,
                        "Вы выбрали дату : ${selectedItem.date}, " +
                                "Температура : ${selectedItem.temp2m.min} – ${selectedItem.temp2m.max} °C",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                binding.weatherRecyclerView.adapter = adapter

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(
                    this@WeatherActivity,
                    "Ошибка загрузки погоды: ${e.localizedMessage}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

}