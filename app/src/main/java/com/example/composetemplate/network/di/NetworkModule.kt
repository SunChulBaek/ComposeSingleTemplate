package com.example.composetemplate.network.di

import com.example.composetemplate.network.SsunNetworkDataSource
import com.example.composetemplate.network.ktor.KtorSsunNetwork
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface SsunNetworkModule {
    @Binds
    fun KtorSsunNetwork.binds(): SsunNetworkDataSource
}