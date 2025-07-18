package com.example.bachelors.features.history.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.example.bachelors.R
import com.example.bachelors.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment(R.layout.fragment_history) {

    private lateinit var binding: FragmentHistoryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentHistoryBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

    }
}