package com.example.puppyappparcial.domain

import com.example.puppyappparcial.data.DogRepository
import com.example.puppyappparcial.domain.models.Publication
import javax.inject.Inject

class GetPublicationUseCase @Inject constructor(private val repository: DogRepository) {

    suspend operator fun invoke(breed: String): Publication {
        return repository.getPublicationFromDataBase("$breed")
    }
}