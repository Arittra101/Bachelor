package com.example.bachelors.network

import com.example.bachelors.network.URL.HomeBaseURL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    companion object{
        fun getRetrofit():Retrofit{
            return Retrofit.Builder().baseUrl(HomeBaseURL.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        }
    }
}