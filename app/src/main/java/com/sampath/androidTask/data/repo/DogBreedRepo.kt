package com.sampath.androidTask.data.repo

import com.sampath.androidTask.data.mappers.toDomain
import com.sampath.androidTask.data.remote.api.DogApiService
import com.sampath.androidTask.domain.model.DogBreed
import com.sampath.androidTask.domain.repository.DogBreedsRepoDomain
import com.sampath.androidTask.utils.ErrorType
import com.sampath.androidTask.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DogBreedRepo @Inject constructor(
    private val dogApi: DogApiService
) : DogBreedsRepoDomain  {
    override suspend fun getDogBreeds(): Resource<List<DogBreed>> {
        return withContext(Dispatchers.IO) {
            val resource = dogApi.listAllBreeds()
            if (resource.isSuccessful) {
                return@withContext Resource.Success(resource.body()!!.toDomain())
            } else {
                return@withContext Resource.Error(message = "Error fetching dog breed data", errorType = ErrorType.UNKNOWN)
            }
        }
    }

}