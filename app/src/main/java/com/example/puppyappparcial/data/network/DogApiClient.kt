package com.example.puppyappparcial.data.network

import com.example.puppyappparcial.data.model.DogModelResponse
import retrofit2.Response
import retrofit2.http.GET

interface DogApiClient {

    @GET("list")
    suspend fun getAllDogs(): Response<DogModelResponse>

//    @GET("$name/images/random/3")
//    suspend fun getImagesByBreed(): Response<DogModel>

}