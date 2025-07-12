package com.example.bachelors.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.example.bachelors.R
import com.example.bachelors.databinding.FragmentHomeActivityBinding
import com.example.bachelors.model.Data


class HomeFragment : Fragment(R.layout.fragment_home_activity) {
    private lateinit var binding: FragmentHomeActivityBinding

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentHomeActivityBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMessPersonalCostData()
        viewModel.data.observe(viewLifecycleOwner){data ->
           binding.date.text = data.data?.get(0)?.CurrentDate.toString()
           binding.hi.text = "Hi "+data.data?.get(0)?.Name.toString()
           binding.give.text = "Rp.  "+data.data?.get(0)?.TotalPaid.toString()+" Tk"
           binding.tvhaveTOPay.text = data.data?.get(0)?.WillGet.toString()+" Tk"
           binding.tvTotalPerMeal.text = data.data?.get(0)?.MealPerPerson.toString()
           binding.tvCurrentPerCost.text = data.data?.get(0)?.totalExpenseBySelf.toString()+" Tk"
           binding.mealRate.text = data.data?.get(0)?.TotalPerMealCost.toString()+" Tk"
           binding.totalExpenses.text = data.data?.get(0)?.TotalExpenses.toString()+" Tk"
           binding.otherExpenses.text = "0.00 Tk"
           binding.totalMeal.text = data.data?.get(0)?.TotalMealInMess.toString()

        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }



    }



    companion object {

    }
}