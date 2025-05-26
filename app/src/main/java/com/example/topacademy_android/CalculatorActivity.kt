package com.example.topacademy_android

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.topacademy_android.databinding.ActivityCalculatorBinding
import com.example.topacademy_android.repositories.CalculatorRepository
import com.example.topacademy_android.useCases.CalculatorUseCase
import com.example.topacademy_android.viewModels.CalculatorViewModel
import kotlinx.coroutines.launch


class CalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculatorBinding

    private val viewModel: CalculatorViewModel by lazy {
        val repository = CalculatorRepository()
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
        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

        // меняем цвет стрелки
        val arrowDrawable = AppCompatResources.getDrawable(this, androidx.appcompat.R.drawable.abc_ic_ab_back_material)
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
            finish(); // Возврат на предыдущую Activity
            return true;
        }
        return super.onOptionsItemSelected(item)
    }


    private fun setupButtons() {
        val buttons = listOf(
            binding.c, binding.backspace, binding.percent, binding.division,
            binding.btn7, binding.btn8, binding.btn9, binding.multiplication,
            binding.btn4, binding.btn5, binding.btn6, binding.subtraction,
            binding.btn1, binding.btn2, binding.btn3, binding.addition,
            binding.btn0, binding.coma, binding.equally
        )

        buttons.forEach { button ->
            button.setOnClickListener {
                val text = button.text.toString()
                when (text) {
                    "=" -> viewModel.evaluate()
                    "C" -> viewModel.clear()
                    "⌫" -> viewModel.backspace()
                    else -> viewModel.appendSymbol(text)
                }
            }
        }
    }





}