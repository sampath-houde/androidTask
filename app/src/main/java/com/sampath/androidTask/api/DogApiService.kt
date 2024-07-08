package com.sampath.androidTask.api

import com.sampath.androidTask.data.model.Breed
import com.sampath.androidTask.data.model.BreedImage
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApiService {

    @GET("breeds/list/all")
    suspend fun listAllBreeds() : Response<Breed>

    @GET("breed/{breed}/images")
    suspend fun getImageByBreed(@Path("breed") breed: String) : Response<BreedImage>

}