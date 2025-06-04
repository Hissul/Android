package com.example.topacademy_android.car.presentation.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topacademy_android.R
import com.example.topacademy_android.car.presentation.adapter.CarAdapter
import com.example.topacademy_android.databinding.ActivityListBinding
import org.koin.android.ext.android.inject


class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding

    // Получаем адаптер через Koin
    private val carAdapter: CarAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolBar = binding.toolBar
        setSupportActionBar(toolBar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val arrowDrawable = AppCompatResources.getDrawable(this, R.drawable.ic_blue_arrow)
        arrowDrawable?.setTint(ContextCompat.getColor(this, R.color.toolbar_icon_color))
        supportActionBar?.setHomeAsUpIndicator(arrowDrawable)

        // RecyclerView с адаптером
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = carAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}