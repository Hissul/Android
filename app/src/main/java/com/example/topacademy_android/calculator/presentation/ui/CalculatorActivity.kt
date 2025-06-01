package com.example.topacademy_android.calculator.presentation.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.topacademy_android.R
import com.example.topacademy_android.databinding.ActivityCalculatorBinding
import com.example.topacademy_android.calculator.data.CalculatorRepositoryImpl
import com.example.topacademy_android.calculator.domain.repository.CalculatorRepository
import com.example.topacademy_android.calculator.domain.use_case.CalculatorUseCase
import com.example.topacademy_android.calculator.presentation.viewmodel.CalculatorViewModel
import kotlinx.coroutines.launch


class CalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculatorBinding

    private val viewModel: CalculatorViewModel by lazy {
        val repository: CalculatorRepository = CalculatorRepositoryImpl()
        val useCase = CalculatorUseCase(repository)
        CalculatorViewModel(useCase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolBar = binding.toolBar
        setSupportActionBar(toolBar)

        // Включаем стрелку "назад"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        // меняем цвет стрелки
        val arrowDrawable = AppCompatResources.getDrawable(
            this,
            R.drawable.ic_blue_arrow
        )
        arrowDrawable?.setTint(ContextCompat.getColor(this, R.color.toolbar_icon_color))
        supportActionBar?.setHomeAsUpIndicator(arrowDrawable)

        // Подписка на обновления выражения
        lifecycleScope.launch {
            viewModel.expression.collect {
                binding.resultView.text = it
            }
        }

        setupButtons()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish() // Возврат на предыдущую Activity
            return true
        }
        return super.onOptionsItemSelected(item)
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

}