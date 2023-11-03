package com.example.puppyappparcial.domain

import com.example.puppyappparcial.data.model.DogModelResponse

data class Dog(val message: List<String>, val status: String)
fun DogModelResponse.toDomain() = Dog(breeds, status)
