package com.example.puppyappparcial.data.network

import com.example.puppyappparcial.data.model.DogModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DogService @Inject constructor(private val apiClient: DogApiClient) {

    suspend fun getAllDogs(): List<DogModel>{
        return withContext(Dispatchers.IO){
            val response = apiClient.getAllDogs()
            response.body() ?: emptyList()
        }
    }
}