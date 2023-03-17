package com.example.composetemplate.network.retrofit

import com.example.composetemplate.network.model.NetworkPhoto
import retrofit2.http.GET

interface ApiService {

    @GET("photos")
    suspend fun getPhotos(): List<NetworkPhoto>
}