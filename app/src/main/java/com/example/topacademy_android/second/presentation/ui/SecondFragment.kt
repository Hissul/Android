package com.example.topacademy_android.second.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.topacademy_android.R
import com.example.topacademy_android.databinding.FragmentSecondBinding
import com.example.topacademy_android.second.presentation.event.SecondUiEvent
import com.example.topacademy_android.second.presentation.view_model.SecondViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SecondViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nameView.text = arguments?.getString("KEY_NAME")

        setupToolbar()
        observeUiEvents()
        setupListeners()
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
            findNavController().navigate(R.id.LoginFragment)
        }
    }

    private fun setupListeners() {
        binding.weatherButton.setOnClickListener {
            viewModel.onWeatherClicked()
        }

        binding.calculatorButton.setOnClickListener {
            viewModel.onCalculatorClicked()
        }

        binding.listButton.setOnClickListener {
            viewModel.onListClicked()
        }
    }

    private fun observeUiEvents() {
        viewModel.uiEvent.observe(viewLifecycleOwner) { event ->
            when (event) {
                SecondUiEvent.NavigateToWeather -> {
                    findNavController().navigate(R.id.action_SecondFragment_to_WeatherFragment)
                }
                SecondUiEvent.NavigateToCalculator -> {
                    findNavController().navigate(R.id.action_SecondFragment_to_CalculatorFragment)
                }
                SecondUiEvent.NavigateToCarList -> {
                    findNavController().navigate(R.id.action_SecondFragment_to_ListFragment)
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}