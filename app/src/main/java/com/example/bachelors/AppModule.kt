package com.example.bachelors

import com.example.bachelors.core.common.data.HomeHistoryRepositoryImp
import com.example.bachelors.core.common.domain.repository.HomeHistoryRepository
import com.example.bachelors.core.common.network.HomeHistoryService
import com.example.bachelors.features.authentication.login.LoginViewModel
import com.example.bachelors.features.common.HomeHistoryViewModel
import com.example.bachelors.features.common.MainViewmodel
import com.google.gson.GsonBuilder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL =
    "https://script.google.com/macros/s/AKfycbzQSZPntuu3LG5HjuN7fx0ACuhQfO1qV0o18aCGqBPLceYnMX4C4hBXvAe253ZeZJbo/exec/"


val appModule = module {

    //we need service for HomeHistoryRepository
    single { provideRetrofit() }

    //create service api instance
    factory { provideApiService(get()) }

    //create repository instance
    single<HomeHistoryRepository> { HomeHistoryRepositoryImp(get()) }

    //create viewmodel instance
    viewModel { HomeHistoryViewModel(get()) }
    viewModel { MainViewmodel(get()) }
    viewModel { LoginViewModel() }

}

fun provideApiService(retrofit: Retrofit): HomeHistoryService =
    retrofit.create(HomeHistoryService::class.java)

fun provideRetrofit(): Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(
        GsonConverterFactory.create(
            GsonBuilder()
                .setLenient()
                .create()
        )
    )
    .build()