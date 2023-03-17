package com.example.composetemplate.data.repository

import com.example.composetemplate.data.model.Photo
import com.example.composetemplate.network.model.NetworkPhoto
import com.example.composetemplate.network.model.asExternalModel
import com.example.composetemplate.network.retrofit.ApiService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeRepository @Inject constructor(
    private val apiService: ApiService
) {

    fun getPhotos(): Flow<List<Photo>> = flow {
        emit(apiService.getPhotos().map(NetworkPhoto::asExternalModel))
    }
}