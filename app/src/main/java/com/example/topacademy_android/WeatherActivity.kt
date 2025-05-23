package com.example.topacademy_android

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topacademy_android.adapters.WeatherAdapter
import com.example.topacademy_android.databinding.ActivityWeatherBinding
import com.example.topacademy_android.network.RetrofitClient
import com.example.topacademy_android.network.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class WeatherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolBar = binding.toolBar
        setSupportActionBar(toolBar)

        // Включаем стрелку "назад"
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

        // меняем цвет стрелки
        val arrowDrawable = AppCompatResources.getDrawable(
            this, androidx.appcompat.R.drawable.abc_ic_ab_back_material)

        arrowDrawable?.setTint(ContextCompat.getColor(this, R.color.toolbar_icon_color))
        supportActionBar?.setHomeAsUpIndicator(arrowDrawable)

        // Гуанчжоу, Китай
        fetchWeather(latitude = 23.09, longitude = 113.17)

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish(); // Возврат на предыдущую Activity
            return true;
        }
        return super.onOptionsItemSelected(item)
    }


    fun fetchWeather(latitude: Double, longitude: Double) {
        val call = RetrofitClient.weatherApi.getWeatherForecast(
            longitude = longitude,
            latitude = latitude
        )

        call.enqueue(object : Callback<WeatherResponse> {

            override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                if (response.isSuccessful) {
                    val weatherData = response.body()

                    val data = weatherData?.dataseries ?: emptyList()

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



                    println("Прогноз погоды: ${weatherData?.dataseries}")


                } else {
                    println("Ошибка: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                println("Ошибка сети: ${t.message}")
            }
        })
    }

}