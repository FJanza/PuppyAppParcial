package com.example.puppyappparcial.domain

import com.example.puppyappparcial.data.DogRepository
import com.example.puppyappparcial.domain.models.Dog
import javax.inject.Inject

class GetDogsUseCase @Inject constructor(private val repository: DogRepository) {

    suspend operator fun invoke(): Dog {
        return repository.getAllDogsFromApi()
    }
}