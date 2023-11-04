package com.example.puppyappparcial.domain

import com.example.puppyappparcial.data.DogRepository
import com.example.puppyappparcial.domain.models.SubBreed
import javax.inject.Inject

class GetSubBreedUseCase @Inject constructor(private val repository: DogRepository) {

    suspend operator fun invoke(breed: String): SubBreed {
        return repository.getAllSubBreedsFromApi("$breed")
    }
}