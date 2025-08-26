package com.example.bachelors

import com.bachelors.authsdk.AuthSDK
import com.example.bachelors.core.common.data.HomeHistoryRepositoryImp
import com.example.bachelors.core.common.data.remote.apiservice.AuthApiService
import com.example.bachelors.core.common.data.repository.AuthRepositoryImpl
import com.example.bachelors.core.common.domain.repository.AuthRepository
import com.example.bachelors.core.common.domain.repository.HomeHistoryRepository
import com.example.bachelors.core.common.domain.usecase.LoginUseCase
import com.example.bachelors.core.common.network.HomeHistoryService
import com.example.bachelors.features.authentication.login.LoginViewModel
import com.example.bachelors.features.common.HomeHistoryViewModel
import com.example.bachelors.features.common.MainViewmodel
import com.example.bachelors.features.historyDetails.data.HistoryDetailsRepoImpl
import com.example.bachelors.features.historyDetails.domain.HistoryDetailsRepo
import com.example.bachelors.features.historyDetails.network.HistoryDetailsService
import com.example.bachelors.features.historyDetails.ui.HistoryDetailsViewmodel
import com.google.gson.GsonBuilder
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL_SCRIPT =
    "https://script.google.com/macros/s/AKfycbzQSZPntuu3LG5HjuN7fx0ACuhQfO1qV0o18aCGqBPLceYnMX4C4hBXvAe253ZeZJbo/exec/"


val appModule = module {

    //we need service for HomeHistoryRepository
    single(named("AppRetrofit")) { provideRetrofit() }

    //create service api instance
    factory { provideApiService(get(named("AppRetrofit"))) }
    //create  History service api instance
    factory { provideHistoryDetailsApiService(get()) }
    //create auth service api instance
    factory { provideAuthApiService(get<Retrofit>()) }


    //create repository instance
    single<HomeHistoryRepository> { HomeHistoryRepositoryImp(get()) }
    //create HistoryDetailsRepo instance
    single<HistoryDetailsRepo> { HistoryDetailsRepoImpl(get()) }
    //create AuthRepo instance
    single<AuthRepository> { AuthRepositoryImpl(get<AuthApiService>()) }

    //create LoginUseCase instance
    single<LoginUseCase> { LoginUseCase(get<AuthRepository>()) }


    //create viewmodel instance
    viewModel { HomeHistoryViewModel(get()) }
    viewModel { MainViewmodel(get()) }
    viewModel { LoginViewModel(get<LoginUseCase>()) }
    viewModel { HistoryDetailsViewmodel(get()) }

}

fun authSdkIntance() = AuthSDK.getInstance()

fun provideApiService(retrofit: Retrofit): HomeHistoryService =
    retrofit.create(HomeHistoryService::class.java)

fun provideHistoryDetailsApiService(retrofit: Retrofit): HistoryDetailsService =
    retrofit.create(HistoryDetailsService::class.java)

fun provideAuthApiService(retrofit: Retrofit): AuthApiService =
    retrofit.create(AuthApiService::class.java)

fun provideRetrofit(): Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL_SCRIPT)
    .addConverterFactory(
        GsonConverterFactory.create(
            GsonBuilder()
                .setLenient()
                .create()
        )
    )
    .build()