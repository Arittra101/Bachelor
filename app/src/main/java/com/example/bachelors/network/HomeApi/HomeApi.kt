package com.example.bachelors.network.HomeApi


import com.example.bachelors.model.BaseResponse
import com.example.bachelors.model.Data
import com.example.bachelors.network.URL.HomeBaseURL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface HomeApi {

    @GET("echo")
    suspend fun getHomeInfo(
        @Query("name") name: String
    ): Response<Data>
}