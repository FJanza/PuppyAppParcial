package com.example.puppyappparcial.domain.models

import com.example.puppyappparcial.data.model.SubBreedResponse

data class SubBreed(val message: List<String>, val status: String)

fun SubBreedResponse.toDomain() = SubBreed(subBreeds,status)
