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

            // Цвет фона и текста в зависимости от температуры
            val (bgColor, textColor) = when {
                item.temp2m.max >= 30 -> R.color.hot to R.color.text_on_hot
                item.temp2m.max >= 20 -> R.color.warm to R.color.text_on_warm
                item.temp2m.max >= 10 -> R.color.cool to R.color.text_on_cool
                else -> R.color.cold to R.color.text_on_cold
            }

            val context = binding.root.context
            binding.root.setCardBackgroundColor(ContextCompat.getColor(context, bgColor))

            val color = ContextCompat.getColor(context, textColor)
            binding.dateTextView.setTextColor(color)
            binding.weatherTextView.setTextColor(color)
            binding.tempTextView.setTextColor(color)
            binding.windTextView.setTextColor(color)

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