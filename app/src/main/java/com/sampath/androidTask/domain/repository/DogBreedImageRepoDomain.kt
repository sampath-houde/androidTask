package com.sampath.androidTask.domain.repository

import com.sampath.androidTask.domain.model.DogBreedImage
import com.sampath.androidTask.utils.Resource

interface DogBreedImageRepoDomain {
    suspend fun getImageByBreed(breed: String): Resource<List<DogBreedImage>>
}
