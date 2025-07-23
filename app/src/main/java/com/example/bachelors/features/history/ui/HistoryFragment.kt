package com.example.bachelors.features.history.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import com.example.bachelors.features.history.compose.MonthHistoryScreen
import com.example.bachelors.ui.theme.MyAppTheme

class HistoryFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
//                val isDarkTheme by remember { mutableStateOf(false) }
                MyAppTheme() {
                    MonthHistoryScreen()
                }
            }
        }

    }
}