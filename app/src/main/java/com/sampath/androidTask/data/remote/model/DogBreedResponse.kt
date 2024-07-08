package com.sampath.androidTask.data.remote.model

data class DogBreedResponse(
    val status: String,
    val message: Map<String, List<String>>
)