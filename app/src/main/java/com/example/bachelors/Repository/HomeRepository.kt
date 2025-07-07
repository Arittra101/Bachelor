package com.example.bachelors.Repository

import com.example.bachelors.network.HomeApi.HomeApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepository(private val homeApi: HomeApi) {

    suspend fun getMessPersonalCostData() = withContext(Dispatchers.IO){
        return@withContext homeApi.getHomeInfo("july").body()
    }
}