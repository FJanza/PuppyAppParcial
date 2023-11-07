package com.example.puppyappparcial.domain

import com.example.puppyappparcial.data.DogRepository
import com.example.puppyappparcial.domain.models.Breed
import com.example.puppyappparcial.domain.models.Publication
import javax.inject.Inject

class GetPublicationsUseCase @Inject constructor(private val repository: DogRepository) {

    suspend operator fun invoke(): List<Publication> {
        val publications = repository.getAllPublicationsFromDataBase()
        if(!publications.isNullOrEmpty()){
            return publications
        }else{
            return listOf()
        }

    }
}