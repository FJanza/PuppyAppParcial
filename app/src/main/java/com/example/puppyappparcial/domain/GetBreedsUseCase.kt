package com.example.puppyappparcial.domain

import com.example.puppyappparcial.data.DogRepository
import com.example.puppyappparcial.domain.models.Breed
import javax.inject.Inject

class GetBreedsUseCase @Inject constructor(private val repository: DogRepository) {

    suspend operator fun invoke(): Breed {
        return repository.getAllBreedsFromApi()
    }
}