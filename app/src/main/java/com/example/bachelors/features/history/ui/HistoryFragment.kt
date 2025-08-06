package com.example.bachelors.features.history.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.findNavController
import com.example.bachelors.features.common.HomeHistoryViewModel
import com.example.bachelors.features.history.compose.MonthHistoryScreenRoute
import com.example.bachelors.ui.theme.MyAppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val navController = findNavController()
                MyAppTheme() {
                    MonthHistoryScreenRoute(navController)
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.wtf("cv","destroy")
    }
}