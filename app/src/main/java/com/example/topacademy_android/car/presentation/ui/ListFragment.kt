package com.example.topacademy_android.car.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topacademy_android.R
import com.example.topacademy_android.car.presentation.adapter.CarAdapter
import org.koin.android.ext.android.inject
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.topacademy_android.databinding.FragmentListBinding


class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    // Получаем адаптер через Koin
    private val carAdapter: CarAdapter by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupToolbar()
        setupRecyclerView()
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

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = carAdapter
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}