package com.example.composetemplate.domain

import com.example.composetemplate.data.FakeRepository
import com.example.composetemplate.data.model.Photo
import com.example.composetemplate.util.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPhotosParam

class GetPhotosUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val repository: FakeRepository
) : FlowUseCase<GetPhotosParam, List<Photo>>(dispatcher) {

    override fun execute(parameters: GetPhotosParam): Flow<Result<List<Photo>>> = flow {
        try {
            val photos = repository.getPhotos()
            emit(Result.success(photos))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}