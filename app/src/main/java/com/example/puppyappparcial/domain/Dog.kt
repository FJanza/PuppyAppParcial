package com.example.puppyappparcial.domain

import com.example.puppyappparcial.data.model.DogModel

data class Dog(val breeds: List<String>, val status: String)

fun DogModel.toDomain() = Dog(breeds, status)
