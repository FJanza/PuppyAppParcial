package com.example.puppyappparcial.data.network

import com.example.puppyappparcial.data.model.DogModelResponse
import com.example.puppyappparcial.data.model.SubBreedResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DogService @Inject constructor(private val apiClient: DogApiClient) {

    suspend fun getAllDogs(): DogModelResponse{
        return withContext(Dispatchers.IO){
            val response = apiClient.getAllDogs()
            response.body()?: DogModelResponse(emptyList(),"Error")
        }
    }

    suspend fun getAllSubBreeds(breed: String): SubBreedResponse{
        return withContext(Dispatchers.IO){
            val response = apiClient.getSubBreeds("$breed")
            response.body()?: SubBreedResponse(emptyList(),"Error")
        }
    }
}