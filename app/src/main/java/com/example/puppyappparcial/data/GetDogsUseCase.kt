package com.example.puppyappparcial.data

import com.example.puppyappparcial.domain.Dog
import javax.inject.Inject

class GetDogsUseCase @Inject constructor(private val repository: DogRepository) {

    suspend operator fun invoke(): Dog {
        return repository.getAllDogsFromApi()
    }
}