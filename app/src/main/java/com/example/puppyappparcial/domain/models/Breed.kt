package com.example.puppyappparcial.domain.models

import com.example.puppyappparcial.data.model.BreedModelResponse

data class Breed(val message: List<String>, val status: String)
fun BreedModelResponse.toDomain() = Breed(breeds, status)
