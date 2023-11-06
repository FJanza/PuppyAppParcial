package com.example.puppyappparcial.data.network

import com.example.puppyappparcial.data.model.BreedModelResponse
import com.example.puppyappparcial.data.model.SubBreedResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DogService @Inject constructor(private val apiClient: DogApiClient) {

    suspend fun getAllBreeds(): BreedModelResponse{
        return withContext(Dispatchers.IO){
            val response = apiClient.getAllBreeds()
            response.body()?: BreedModelResponse(emptyList(),"Error")
        }
    }

    suspend fun getAllSubBreeds(breed: String): SubBreedResponse{
        return withContext(Dispatchers.IO){
            val response = apiClient.getSubBreeds("$breed")
            response.body()?: SubBreedResponse(emptyList(),"Error")
        }
    }
}