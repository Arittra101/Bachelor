package com.example.bachelors.features.common

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bachelors.core.common.data.model.HistoryItem
import com.example.bachelors.core.common.data.model.Months
import com.example.bachelors.core.common.domain.repository.HomeHistoryRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/*this codebase is congested because slow api response dependency.
after we complete backend viewmodel will be separated for each screen*/
class HomeHistoryViewModel(
    private val homeHistoryRepository: HomeHistoryRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeHistoryUiState>(HomeHistoryUiState.Loading)
    val uiState = _uiState


    private val _uiEffect = MutableSharedFlow<HomeUiEffect>()

    val uiEffect = _uiEffect
    var isDataLoaded = false;

    //for history fragment
    var historyState by mutableStateOf(HistoryState())


    init {
        viewModelScope.launch {
            homeHistoryRepository.getMonthList("allsheetsname").collect{
                Log.wtf("crey","get all month from history UI ${it}")
                fetchHistory("Arittra", "april","allsheets")
            }
        }
    }


    fun fetchUserCurrentMealInfo(
        name: String,
        action: String,
    ) {
        Log.wtf("crey","going for fetchUserCurrentMealInfo")
        viewModelScope.launch {
            homeHistoryRepository.getUserCurrentMealInfo(name, action)
                .collect { result ->
                    _uiState.value = HomeHistoryUiState.Success(result)
                    _uiEffect.emit(HomeUiEffect.ShowSuccess("Data fetched successfully"))
                    isDataLoaded = true
                }
        }
    }

    fun fetchHistory(
        name:String,
        month: String,
        action: String
    ){
        Log.wtf("crey","going for fetchHistory")
        viewModelScope.launch {
            homeHistoryRepository.getHistory(name,month,action).collect{
                Log.wtf("crey","get history from home history viewmodel ${it}")
                historyState = historyState.copy(historyItem = it)
            }
        }
    }

    fun handleHomeIntent(event: HomeIntent){
        when(event){
            is HomeIntent.LoadData -> {
                fetchUserCurrentMealInfo(event.name, event.action)
            }
        }
    }

    fun handleHistoryEvent(event: HistoryScreenEvent) {
        when (event) {
            is HistoryScreenEvent.FetchHistory -> {
                fetchHistory(event.name, event.month, event.action)
            }
        }
    }




}
//for History Fragment
data class HistoryState(
    val historyItem: List<HistoryItem>?=null,
    val isLoading: Boolean = true,
)
sealed class HistoryScreenEvent {
    data class FetchHistory(
        val name: String,
        val month: String,
        val action: String
    ) : HistoryScreenEvent()
}

//for HomeFragment
sealed class HomeHistoryUiState {
    data object Loading : HomeHistoryUiState()
    data class Success(val data: List<Any> = emptyList()) : HomeHistoryUiState()
    data class Error(val message: String) : HomeHistoryUiState()
}

sealed class HomeIntent {
    data class LoadData(val name: String, val month: String, val action: String) : HomeIntent()
}

sealed class HomeUiEffect{
    data class ShowError(val message: String) : HomeUiEffect()
    data class ShowSuccess(val message: String) : HomeUiEffect()
}