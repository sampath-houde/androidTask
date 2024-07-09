package com.sampath.androidTask.data.repo

import com.sampath.androidTask.data.mappers.toDomain
import com.sampath.androidTask.data.remote.api.DogApiService
import com.sampath.androidTask.domain.model.DogBreedImage
import com.sampath.androidTask.domain.repository.DogBreedImageRepoDomain
import com.sampath.androidTask.utils.ErrorType
import com.sampath.androidTask.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DogBreedImageRepo @Inject constructor(
    private val dogApi: DogApiService
) : DogBreedImageRepoDomain {
    override suspend fun getImageByBreed(breed: String): Resource<DogBreedImage> {
        return withContext(Dispatchers.IO) {
            val resource = dogApi.getImageByBreed(breed)
            if (resource.isSuccessful) {
                return@withContext Resource.Success(resource.body()!!.toDomain())
            } else {
                return@withContext Resource.Error(
                    message = "Error fetching dog breed image url",
                    errorType = ErrorType.UNKNOWN
                )
            }
        }
    }

    override suspend fun getImagedBySubBreed(
        breed: String,
        subBreed: String,
    ): Resource<DogBreedImage> {
        return withContext(Dispatchers.IO) {
            val resource = dogApi.getImagesBySubBreed(breed, subBreed)
            if (resource.isSuccessful) {
                return@withContext Resource.Success(resource.body()!!.toDomain())
            } else {
                return@withContext Resource.Error(
                    message = "Error fetching dog breed image url",
                    errorType = ErrorType.UNKNOWN
                )
            }
        }
    }
}
