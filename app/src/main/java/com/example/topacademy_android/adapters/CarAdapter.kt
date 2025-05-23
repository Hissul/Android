package com.example.topacademy_android.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.topacademy_android.Car
import com.example.topacademy_android.databinding.ItemCarBinding

class CarAdapter(private val cars : List<Car>) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    inner class CarViewHolder(private val binding: ItemCarBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(car: Car) {
            binding.textBrandModel.text = "${car.brand} ${car.model}"
            binding.textYear.text = "Year: ${car.year}"
            binding.textDescription.text = car.description
            binding.textCost.text = "Price: $${car.cost}"
            binding.imageCar.setImageResource(car.imageResId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = ItemCarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarViewHolder(binding)
    }

    override fun getItemCount(): Int = cars.size

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(cars[position])
    }
}