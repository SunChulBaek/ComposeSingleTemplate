package com.example.composetemplate.di

import com.example.composetemplate.BuildConfig
import com.example.composetemplate.network.retrofit.RetrofitSsunNetwork
import com.example.composetemplate.data.repository.FakeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(httpLoggingInterceptor)
            }
        }
        .build()
    }

    @Singleton
    @Provides
    fun providesFakeRepository(
        apiService: RetrofitSsunNetwork
    ): FakeRepository {
        return FakeRepository(apiService)
    }
}