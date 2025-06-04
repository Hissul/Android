package com.example.topacademy_android.main.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.appcompat.app.AppCompatActivity
import com.example.topacademy_android.second.presentation.ui.SecondActivity
import com.example.topacademy_android.databinding.ActivityMainBinding
import com.example.topacademy_android.main.presentation.event.LoginUiEvent
import com.example.topacademy_android.main.presentation.view_model.LoginViewModel

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.uiEvent.observe(this) { event ->
            when (event) {
                is LoginUiEvent.ShowToast -> {
                    Toast.makeText(this, event.message, Toast.LENGTH_SHORT).show()
                }
                is LoginUiEvent.NavigateToSecond -> {
                    val intent = Intent(this, SecondActivity::class.java)
                    intent.putExtra("KEY_NAME", event.username)
                    startActivity(intent)
                }
            }
        }
        binding.startButton.setOnClickListener {
            viewModel.validate(
                binding.emailInput.text.toString(),
                binding.passwordInput.text.toString()
            )
        }
    }

}