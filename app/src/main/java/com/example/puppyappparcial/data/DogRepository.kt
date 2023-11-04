package com.example.puppyappparcial.data

import com.example.puppyappparcial.data.database.dao.DogDao
import com.example.puppyappparcial.data.database.entities.DogEntity
import com.example.puppyappparcial.data.model.BreedModelResponse
import com.example.puppyappparcial.data.model.SubBreedResponse
import com.example.puppyappparcial.data.network.DogService
import com.example.puppyappparcial.domain.models.Breed
import com.example.puppyappparcial.domain.models.Publication
import com.example.puppyappparcial.domain.models.SubBreed
import com.example.puppyappparcial.domain.models.toDomain
import javax.inject.Inject

class DogRepository @Inject constructor(
    private val remote: DogService,
    private val dogDao: DogDao
) {

    suspend fun getAllBreedsFromApi(): Breed {
        val response: BreedModelResponse = remote.getAllBreeds()
        return response.toDomain()
    }

    suspend fun getAllSubBreedsFromApi(breed: String): SubBreed {
        val response: SubBreedResponse = remote.getAllSubBreeds("$breed")
        return response.toDomain()
    }

    suspend fun getAllPublicationsFromDataBase(): List<Publication> {
        val response: List<DogEntity> = dogDao.getAllDogs()
        return response.map { it.toDomain() }
    }
}