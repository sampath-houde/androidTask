package com.sampath.androidTask.api

import com.sampath.androidTask.data.model.Breed
import com.sampath.androidTask.data.model.BreedImage
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApiService {

    @GET("breeds/list/all")
    fun listAllBreeds() : Breed

    @GET("breed/{breed}/images")
    fun getImageByBreed(@Path("breed") breed: String) : BreedImage

}