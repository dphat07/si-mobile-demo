package com.example.si_mobile_demo.di

import com.example.si_mobile_demo.BuildConfig
import com.example.si_mobile_demo.data.remote.AuthApiService
import com.example.si_mobile_demo.data.remote.AuthInterceptor
import com.example.si_mobile_demo.data.repository.AuthRepositoryImpl
import com.example.si_mobile_demo.domain.repository.AuthRepository
import com.example.si_mobile_demo.ui.screen.login.LoginViewModel
import com.example.si_mobile_demo.ui.screen.select_class.SelectClassViewModel
import com.google.firebase.auth.FirebaseAuth
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { AuthInterceptor(get()) }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<AuthInterceptor>())
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApiService::class.java)
    }
}

val repositoryModule = module {
    single { FirebaseAuth.getInstance() }
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }
}

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { SelectClassViewModel(get()) }
}
val appModules = listOf(networkModule, repositoryModule, viewModelModule)
