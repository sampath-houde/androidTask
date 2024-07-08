package com.sampath.androidTask.domain.model

data class DogBreed(
    val name: String,
    val subBreed: List<DogSubBreed>
)

data class DogSubBreed(
    val name: String
)
