package com.example.puppyappparcial.data.network

import com.example.puppyappparcial.data.model.DogModelResponse
import com.example.puppyappparcial.data.model.SubBreedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApiClient {

    @GET("breeds/list")
    suspend fun getAllDogs(): Response<DogModelResponse>

    @GET("breed/{breed}/list")
    suspend fun getSubBreeds(@Path("breed") breed: String): Response<SubBreedResponse>

}