package com.example.puppyappparcial.data

import com.example.puppyappparcial.data.model.DogModel
import com.example.puppyappparcial.data.network.DogService
import com.example.puppyappparcial.domain.Dog
import com.example.puppyappparcial.domain.toDomain
import javax.inject.Inject

class DogRepository @Inject constructor(
    private val remote: DogService
) {

    suspend fun getAllDogsFromApi(): List<Dog>{
        val response: List<DogModel> = remote.getAllDogs()
        return response.map { it.toDomain() }
    }
}