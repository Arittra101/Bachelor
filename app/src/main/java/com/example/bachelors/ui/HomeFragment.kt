package com.example.bachelors.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.bachelors.R
import com.example.bachelors.core.common.data.model.Months
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.bachelors.core.common.data.model.UserInfo
import com.example.bachelors.databinding.FragmentHomeActivityBinding
import com.example.bachelors.features.common.HomeHistoryUiState
import com.example.bachelors.features.common.HomeHistoryViewModel
import com.example.bachelors.features.common.HomeUiEffect
import kotlinx.coroutines.launch

class HomeFragment : Fragment(R.layout.fragment_home_activity) {
    private lateinit var binding: FragmentHomeActivityBinding
    private val viewModel: HomeHistoryViewModel by viewModel()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentHomeActivityBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
        observe()

        if(viewModel.isDataLoaded) return
        viewModel.fetchUserCurrentMealInfo("Dhrubo","getMemberCost")
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    //check only those data which can be cast else filter it out
                    is HomeHistoryUiState.Success -> {
                        val userList = (state.data as? List<*>)?.mapNotNull { it as? UserInfo }
                        showData(userList.orEmpty())
                    }
                    is HomeHistoryUiState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is HomeHistoryUiState.Error -> TODO()
                }

            }
        }


        lifecycleScope.launch {
            viewModel.uiEffect.collect { state ->
                when (state) {
                    is HomeUiEffect.ShowSuccess -> {
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                    }
                    is HomeUiEffect.ShowError -> {
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showData(data: List<UserInfo>) {
        binding.progressBar.visibility = View.GONE
        binding.date.text = data[0].CurrentDate.toString()
        binding.hi.text = "Hi " + data.get(0).Name.toString()
        binding.give.text = "Rp.  " + data.get(0).TotalPaid.toString() + " Tk"
        binding.tvhaveTOPay.text = data.get(0).WillGet.toString() + " Tk"
        binding.tvTotalPerMeal.text = data.get(0).MealPerPerson.toString()
        binding.tvCurrentPerCost.text = data.get(0).totalExpenseBySelf.toString() + " Tk"
        binding.mealRate.text = data.get(0).TotalPerMealCost.toString() + " Tk"
        binding.totalExpenses.text = data.get(0).TotalExpenses.toString() + " Tk"
        binding.otherExpenses.text = "0.00 Tk"
        binding.totalMeal.text = data.get(0).TotalMealInMess.toString()
    }
}