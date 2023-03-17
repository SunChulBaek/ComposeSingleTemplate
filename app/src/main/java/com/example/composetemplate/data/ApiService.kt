package com.example.composetemplate.data

import retrofit2.http.GET
import com.example.composetemplate.network.model.NetworkPhoto

interface ApiService {

    @GET("photos")
    suspend fun getPhotos(): List<NetworkPhoto>
}