package com.example.puppyappparcial.data.network

import com.example.puppyappparcial.data.model.DogModelResponse
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
}