package com.example.composetemplate.data

import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import com.example.composetemplate.data.model.Photo

interface ApiService {

    @GET("photos")
    suspend fun getPhotos(): List<Photo>
}