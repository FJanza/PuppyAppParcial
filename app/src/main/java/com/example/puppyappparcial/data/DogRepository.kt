package com.example.puppyappparcial.data

import com.example.puppyappparcial.data.model.DogModelResponse
import com.example.puppyappparcial.data.model.SubBreedResponse
import com.example.puppyappparcial.data.network.DogService
import com.example.puppyappparcial.domain.Dog
import com.example.puppyappparcial.domain.SubBreed
import com.example.puppyappparcial.domain.toDomain
import javax.inject.Inject

class DogRepository @Inject constructor(
    private val remote: DogService
) {

    suspend fun getAllDogsFromApi(): Dog {
        val response: DogModelResponse = remote.getAllDogs()
        return response.toDomain()
    }

    suspend fun getAllSubBreedsFromApi(breed: String): SubBreed{
        val response: SubBreedResponse = remote.getAllSubBreeds("$breed")
        return response.toDomain()
    }
}