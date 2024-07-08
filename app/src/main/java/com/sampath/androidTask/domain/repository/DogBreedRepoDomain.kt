package com.sampath.androidTask.domain.repository

import com.sampath.androidTask.domain.model.DogBreed
import com.sampath.androidTask.utils.Resource

interface DogBreedsRepoDomain {
    suspend fun getDogBreeds(): Resource<List<DogBreed>>
}
