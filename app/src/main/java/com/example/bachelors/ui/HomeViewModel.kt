package com.example.bachelors.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bachelors.Repository.HomeRepository
import com.example.bachelors.model.BaseResponse
import com.example.bachelors.model.Data
import com.example.bachelors.network.ApiClient
import com.example.bachelors.network.HomeApi.HomeApi
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _data: MutableLiveData<Data> by lazy {
        MutableLiveData<Data>()
    }
    val data: LiveData<Data>
        get() = _data

    private val apiClient = ApiClient.getRetrofit().create(HomeApi::class.java)

    private val homeRepository = HomeRepository(apiClient)

    fun getMessPersonalCostData() = viewModelScope.launch {

        _data.value = homeRepository.getMessPersonalCostData()
        println("Our Data is ${_data.value}")
    }
}