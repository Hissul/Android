package com.example.topacademy_android

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import com.example.topacademy_android.databinding.ActivityCalculatorBinding


class CalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCalculatorBinding.inflate(layoutInflater)
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
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish(); // Возврат на предыдущую Activity
            return true;
        }
        return super.onOptionsItemSelected(item)
    }


}