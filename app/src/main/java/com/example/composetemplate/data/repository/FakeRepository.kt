package com.example.composetemplate.data.repository

import com.example.composetemplate.data.model.Photo
import com.example.composetemplate.network.SsunNetworkDataSource
import com.example.composetemplate.network.model.NetworkPhoto
import com.example.composetemplate.network.model.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeRepository @Inject constructor(
    private val network: SsunNetworkDataSource
) {

    fun getPhotos(): Flow<List<Photo>> = flow {
        emit(network.getPhotos().map(NetworkPhoto::asExternalModel))
    }
}