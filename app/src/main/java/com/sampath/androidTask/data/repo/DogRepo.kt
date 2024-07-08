package com.sampath.androidTask.data.repo

import com.sampath.androidTask.api.DogApiService
import com.sampath.androidTask.utils.BaseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DogRepo @Inject constructor(
    private val dogApi: DogApiService
) : BaseRepository() {
    suspend fun listAllDogs() = safeApiCall {
        withContext(Dispatchers.IO) {
            dogApi.listAllBreeds()
        }
    }
    suspend fun getImageByBreed(breed: String) = safeApiCall {
        withContext(Dispatchers.IO) {
            dogApi.getImageByBreed(breed)
        }
    }
}