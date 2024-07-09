package com.sampath.androidTask.data.remote.api

import com.sampath.androidTask.data.remote.model.DogBreedResponse
import com.sampath.androidTask.data.remote.model.DogBreedImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApiService {

    @GET("breeds/list/all")
    suspend fun listAllBreeds() : Response<DogBreedResponse>

    @GET("breed/{breed}/images")
    suspend fun getImageByBreed(@Path("breed") breed: String) : Response<DogBreedImageResponse>

}