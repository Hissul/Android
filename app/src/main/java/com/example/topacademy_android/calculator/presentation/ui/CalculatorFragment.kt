package com.example.topacademy_android.calculator.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.topacademy_android.R
import com.example.topacademy_android.calculator.domain.repository.CalculatorRepository
import com.example.topacademy_android.calculator.presentation.viewmodel.CalculatorViewModel
import com.example.topacademy_android.databinding.FragmentCalculatorBinding
import com.example.topacademy_android.calculator.data.CalculatorRepositoryImpl
import com.example.topacademy_android.calculator.domain.use_case.CalculatorUseCase
import kotlinx.coroutines.launch


class CalculatorFragment : Fragment() {

    private var _binding: FragmentCalculatorBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CalculatorViewModel by lazy {
        val repository: CalculatorRepository = CalculatorRepositoryImpl()
        val useCase = CalculatorUseCase(repository)
        CalculatorViewModel(useCase)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalculatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        observeViewModel()
        setupButtons()


    }

    private fun setupToolbar() {
        (activity as? AppCompatActivity)?.apply {
            setSupportActionBar(binding.toolBar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)

            val arrowDrawable = AppCompatResources.getDrawable(this, R.drawable.ic_blue_arrow)
            arrowDrawable?.setTint(ContextCompat.getColor(this, R.color.toolbar_icon_color))
            supportActionBar?.setHomeAsUpIndicator(arrowDrawable)
        }

        binding.toolBar.setNavigationOnClickListener {
            //findNavController().navigate(R.id.SecondFragment)
            findNavController().navigateUp()

        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.expression.collect {
                binding.resultView.text = it
            }
        }
    }

    private fun setupButtons() {
        val buttons = listOf(
            binding.c, binding.backspace, binding.percent, binding.division,
            binding.btn7, binding.btn8, binding.btn9, binding.multiplication,
            binding.btn4, binding.btn5, binding.btn6, binding.subtraction,
            binding.btn1, binding.btn2, binding.btn3, binding.addition,
            binding.btn0, binding.coma, binding.plusMinus, binding.equally
        )

        buttons.forEach { button ->
            button.setOnClickListener {
                when (button.id) {
                    R.id.plusMinus -> binding.resultView.append("-")
                    R.id.btn1 -> binding.resultView.append("1")
                    R.id.btn2 -> binding.resultView.append("2")
                    R.id.btn3 -> binding.resultView.append("3")
                    R.id.btn4 -> binding.resultView.append("4")
                    R.id.btn5 -> binding.resultView.append("5")
                    R.id.btn6 -> binding.resultView.append("6")
                    R.id.btn7 -> binding.resultView.append("7")
                    R.id.btn8 -> binding.resultView.append("8")
                    R.id.btn9 -> binding.resultView.append("9")
                    R.id.btn0 -> binding.resultView.append("0")
                    R.id.percent -> binding.resultView.append("%")
                    R.id.division -> binding.resultView.append("/")
                    R.id.multiplication -> binding.resultView.append("*")
                    R.id.subtraction -> binding.resultView.append("-")
                    R.id.addition -> binding.resultView.append("+")
                    R.id.coma -> binding.resultView.append(".")
                    R.id.equally -> viewModel.evaluate(binding.resultView.text.toString())
                    R.id.c -> {
                        viewModel.clear()
                        binding.resultView.text = ""
                    }
                    R.id.backspace -> {
                        viewModel.backspace()
                        val currentText = binding.resultView.text.toString()
                        if (currentText.isNotEmpty()) {
                            binding.resultView.text = currentText.dropLast(1)
                        }
                    }
                    else -> {}
                }
            }
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}