package com.example.composetemplate.data

import javax.inject.Inject
import javax.inject.Singleton
import com.example.composetemplate.data.model.Photo

@Singleton
class FakeRepository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getPhotos(): List<Photo> = apiService.getPhotos()
}