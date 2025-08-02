package com.example.bachelors.features.historyDetails.ui
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.bachelors.features.historyDetails.screen.HistoryDetailsRoutes
import com.example.bachelors.ui.theme.MyAppTheme

class HistoryDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val navController = findNavController()
                MyAppTheme() {
                    HistoryDetailsRoutes(navController)
                }
            }
        }

    }
}

