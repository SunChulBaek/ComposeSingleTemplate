package com.example.composetemplate.network

import com.example.composetemplate.network.model.NetworkPhoto

interface SsunNetworkDataSource {
    suspend fun getPhotos(): List<NetworkPhoto>
}