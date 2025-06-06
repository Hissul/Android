package com.example.topacademy_android.weather.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topacademy_android.R
import com.example.topacademy_android.databinding.FragmentWeatherBinding
import com.example.topacademy_android.weather.domain.adapter.WeatherAdapter
import com.example.topacademy_android.weather.presentation.viewmodel.WeatherViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.navigation.fragment.findNavController


class WeatherFragment : Fragment() {

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WeatherViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        setupToolbar()
        observeWeather()
        viewModel.fetchWeather(23.09, 113.17)

        setHasOptionsMenu(true)
    }


    private fun setupToolbar() {
        (activity as? AppCompatActivity)?.apply {
            setSupportActionBar(binding.toolBar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
            val arrowDrawable = AppCompatResources.getDrawable(
                this, R.drawable.ic_blue_arrow
            )
            arrowDrawable?.setTint(ContextCompat.getColor(this, R.color.toolbar_icon_color))
            supportActionBar?.setHomeAsUpIndicator(arrowDrawable)
        }

        binding.toolBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun observeWeather() {
        lifecycleScope.launch {
            viewModel.weatherList.collect { data ->
                val adapter = WeatherAdapter(data) { selectedItem ->
                    Toast.makeText(
                        requireContext(),
                        "Вы выбрали дату : ${selectedItem.date}, " +
                                "Температура : ${selectedItem.temp2m.min} – ${selectedItem.temp2m.max} °C",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                binding.weatherRecyclerView.layoutManager = LinearLayoutManager(requireContext())
                binding.weatherRecyclerView.adapter = adapter
            }
        }

        lifecycleScope.launch {
            viewModel.error.collect { message ->
                message?.let {
                    Toast.makeText(requireContext(), "Ошибка: $it", Toast.LENGTH_LONG).show()
                }
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}