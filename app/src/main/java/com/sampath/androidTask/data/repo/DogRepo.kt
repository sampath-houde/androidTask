package com.sampath.androidTask.data.repo

import com.sampath.androidTask.api.DogApiService
import com.sampath.androidTask.data.model.Breed
import com.sampath.androidTask.data.model.BreedImage
import com.sampath.androidTask.utils.ErrorType
import com.sampath.androidTask.utils.NetworkUtils
import com.sampath.androidTask.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DogRepo @Inject constructor(
    private val dogApi: DogApiService,
    private val networkUtils: NetworkUtils
)  {
    suspend fun listAllDogs() =
        withContext(Dispatchers.IO) {
            val resource = dogApi.listAllBreeds()
            if (resource.isSuccessful) {
                return@withContext Resource.Success(resource.body()!!)
            } else {
                return@withContext Resource.Error(message = "Error fetching dog breed data", errorType = ErrorType.UNKNOWN)
            }
        }
    suspend fun getImageByBreed(breed: String) =
        withContext(Dispatchers.IO) {
            val resource = dogApi.getImageByBreed(breed)
            if (resource.isSuccessful) {
                return@withContext Resource.Success(resource.body()!!)
            } else {
                return@withContext Resource.Error(message = "Error fetching dog breed image url", errorType = ErrorType.UNKNOWN)
            }
        }

}