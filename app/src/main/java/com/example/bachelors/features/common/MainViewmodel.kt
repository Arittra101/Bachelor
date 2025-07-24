package com.example.bachelors.features.common
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bachelors.core.common.allSheets
import com.example.bachelors.core.common.allSheets.allMonths
import com.example.bachelors.core.common.data.model.Months
import com.example.bachelors.core.common.domain.repository.HomeHistoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class MainViewmodel(private val homeHistoryRepository: HomeHistoryRepository) : ViewModel() {

    private val _isFetchDone = MutableStateFlow(false)
    val isFetchDone = _isFetchDone
    private val monthList = MutableStateFlow<List<String>>(listOf())

    init {
        viewModelScope.launch {
            fetchMonthList( "allsheetsname")
        }
    }

    private suspend fun fetchMonthList(action: String) {
        homeHistoryRepository.getMonthList(action).collect {
            isFetchDone.value = true
            monthList.value = it
            Months.months = it
            allMonths = it
        }
    }

}
