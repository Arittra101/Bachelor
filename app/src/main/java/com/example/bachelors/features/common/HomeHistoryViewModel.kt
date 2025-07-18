package com.example.bachelors.features.common

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bachelors.core.common.data.model.Months
import com.example.bachelors.core.common.domain.repository.HomeHistoryRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeHistoryViewModel(
    private val homeHistoryRepository: HomeHistoryRepository,
    /* private val savedStateHandle: SavedStateHandle*/
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeHistoryUiState>(HomeHistoryUiState.Loading)
    val uiState = _uiState

    private val _uiEffect = MutableSharedFlow<HomeUiEffect>()
    val uiEffect = _uiEffect

    var isDataLoaded = false;


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
        viewModelScope.launch {
            homeHistoryRepository.getHistory(name,month,action).collect{
                _uiState.value = HomeHistoryUiState.Success(it)
                _uiEffect.emit(HomeUiEffect.ShowSuccess("Data fetched successfully"))
                isDataLoaded = true
            }

        }
    }

    //from ui to viewmodel
    fun handleHomeIntent(event: HomeIntent){
        when(event){
            is HomeIntent.LoadData -> {
                fetchUserCurrentMealInfo(event.name, event.action)
            }
        }
    }


}

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