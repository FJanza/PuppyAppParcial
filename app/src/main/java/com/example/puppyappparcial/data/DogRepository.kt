package com.example.puppyappparcial.data

import com.example.puppyappparcial.data.database.dao.PublicationDao
import com.example.puppyappparcial.data.database.entities.PublicationEntity
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
    private val publicationDao: PublicationDao
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
        val response: List<PublicationEntity> = publicationDao.getAllPublications()
        return response.map { it.toDomain() }
    }

    suspend fun insertPublication(publication : PublicationEntity) {
        publicationDao.insertPublication(publication)
    }

    suspend fun updateOwner(id : Int, owner: String) {
        publicationDao.updateOwner(owner, id)
    }

    suspend fun toggleFavorite(id : Int) {
        publicationDao.toggleFavorite(id)
    }

}