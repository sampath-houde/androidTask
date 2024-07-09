package com.sampath.androidTask.data.mappers

import com.sampath.androidTask.data.remote.model.DogBreedImageResponse
import com.sampath.androidTask.data.remote.model.DogBreedResponse
import com.sampath.androidTask.domain.model.DogBreed
import com.sampath.androidTask.domain.model.DogBreedImage
import com.sampath.androidTask.domain.model.DogSubBreed

fun DogBreedResponse.toDomain(): List<DogBreed> {
    return this.message.entries.map {
        val subBreed = it.value.map { sub ->
            DogSubBreed(sub)
        }
        DogBreed(it.key, subBreed)
    }
}

fun DogBreedImageResponse.toDomain(): DogBreedImage {
    return DogBreedImage(imageUrl = this.message)
}
