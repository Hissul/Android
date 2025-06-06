package com.example.topacademy_android.main.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.topacademy_android.R
import com.example.topacademy_android.databinding.FragmentLoginBinding
import com.example.topacademy_android.main.presentation.view_model.LoginViewModel
import com.example.topacademy_android.main.presentation.event.LoginUiEvent
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startButton.setOnClickListener {
            viewModel.validate(
                binding.emailInput.text.toString(),
                binding.passwordInput.text.toString()
            )
        }

        // Пример наблюдения за событиями (если ViewModel использует StateFlow/EventFlow)
        viewModel.uiEvent.observe(viewLifecycleOwner) { event ->
            when (event) {
                is LoginUiEvent.NavigateToSecond -> {
                    Toast.makeText(requireContext(), "Добро пожаловать!", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_LoginFragment_to_MainScreenFragment)
                }
                is LoginUiEvent.ShowToast -> {
                    Toast.makeText(requireContext(), event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}