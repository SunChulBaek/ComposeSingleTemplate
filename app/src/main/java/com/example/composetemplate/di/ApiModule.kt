package com.example.composetemplate.di

import com.example.composetemplate.network.ktor.KtorSsunNetwork
import com.example.composetemplate.data.repository.FakeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun providesFakeRepository(
        apiService: KtorSsunNetwork
    ): FakeRepository {
        return FakeRepository(apiService)
    }
}