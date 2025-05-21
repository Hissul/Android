package com.example.topacademy_android

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topacademy_android.databinding.ActivityListBinding


class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolBar = binding.toolBar
        setSupportActionBar(toolBar)

        // Включаем стрелку "назад"
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

        // меняем цвет стрелки
        val arrowDrawable = AppCompatResources.getDrawable(this, androidx.appcompat.R.drawable.abc_ic_ab_back_material)
        arrowDrawable?.setTint(ContextCompat.getColor(this, R.color.toolbar_icon_color))
        supportActionBar?.setHomeAsUpIndicator(arrowDrawable)

        // Моковые данные
        val cars = listOf(
            Car("BMW", "X5", 2020, "Luxury SUV", 55000, R.drawable.ic_launcher_foreground),
            Car("Audi", "A4", 2019, "Sedan with quattro", 40000, R.drawable.ic_launcher_foreground),
            Car("Toyota", "Camry", 2021, "Reliable car", 30000, R.drawable.ic_launcher_foreground),
            Car("Tesla", "Model S", 2022, "Electric performance", 80000, R.drawable.ic_launcher_foreground)
        )

        // Настройка RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = CarAdapter(cars)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish(); // Возврат на предыдущую Activity
            return true;
        }
        return super.onOptionsItemSelected(item)
    }
}