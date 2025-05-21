package com.example.topacademy_android

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.topacademy_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        binding.startButton.setOnClickListener{
            validateInput()
        }
    }

    private fun validateInput() {
        val email = binding.emailInput.text.toString()
        val password = binding.passwordInput.text.toString()

        var isValid = true

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.emailLayout.error = "Введите корректный email"
            isValid = false
        }
        else{
            binding.emailLayout.error = null
        }

        if(password.length <= 6){
            binding.passwordLayout.error = "Пароль должен быть больше 6 символов"
            isValid = false
        }
        else{
            binding.passwordLayout.error = null
        }

        // старт SecondAcnivity если все
        if(isValid){

            binding.emailLayout.error = null
            binding.passwordLayout.error = null
            Toast.makeText(this, "Добро пожаловать!", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, SecondActivity::class.java)

            val name = binding.emailInput.text.toString()
            if(name.isNotEmpty()){
                intent.putExtra("KEY_NAME", name)
            }
            startActivity(intent)
        }

    }

}