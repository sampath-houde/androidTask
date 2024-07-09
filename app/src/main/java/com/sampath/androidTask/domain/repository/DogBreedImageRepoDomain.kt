package com.sampath.androidTask.domain.repository

import com.sampath.androidTask.data.remote.model.DogBreedImageResponse
import com.sampath.androidTask.domain.model.DogBreedImage
import com.sampath.androidTask.utils.Resource
import kotlinx.coroutines.flow.Flow

interface DogBreedImageRepoDomain {
    suspend fun getImageByBreed(breed: String): Resource<DogBreedImage>
    suspend fun getImagedBySubBreed(breed: String, subBreed: String) : Resource<DogBreedImage>
}
