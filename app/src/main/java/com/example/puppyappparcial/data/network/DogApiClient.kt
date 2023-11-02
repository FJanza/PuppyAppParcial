package com.example.puppyappparcial.data.network

import com.example.puppyappparcial.data.model.DogModel
import retrofit2.Response
import retrofit2.http.GET

interface DogApiClient {

    @GET("list/all")
    suspend fun getAllDogs(): Response<List<DogModel>>

//    @GET("$name/images/random/3")
//    suspend fun getImagesByBreed(): Response<DogModel>

}