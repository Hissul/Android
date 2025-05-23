package com.example.topacademy_android.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.topacademy_android.R
import com.example.topacademy_android.databinding.ItemWeatherBinding
import com.example.topacademy_android.network.DataSeries

class WeatherAdapter (private val data: List<DataSeries>, private val onItemClick: (DataSeries) -> Unit) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    inner class WeatherViewHolder(val binding: ItemWeatherBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(item: DataSeries) {

            binding.dateTextView.text = "Дата: ${item.date}"
            binding.weatherTextView.text = "Погода: ${item.weather}"
            binding.tempTextView.text = "Темп: ${item.temp2m.min} – ${item.temp2m.max} °C"
            binding.windTextView.text = "Ветер: до ${item.wind10m_max} м/с"

            // Цвет фона в зависимости от температуры
            val bgColor = when {
                item.temp2m.max >= 30 -> R.color.hot
                item.temp2m.max >= 20 -> R.color.warm
                item.temp2m.max >= 10 -> R.color.cool
                else -> R.color.cold
            }
            binding.root.setCardBackgroundColor(
                ContextCompat.getColor(binding.root.context, bgColor))

            // Обработчик нажатия
            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(data[position])
    }


}