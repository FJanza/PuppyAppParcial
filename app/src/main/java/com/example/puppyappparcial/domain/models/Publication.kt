package com.example.puppyappparcial.domain.models

import com.example.puppyappparcial.data.database.entities.PublicationEntity


data class Publication(
    val id : Int,
    val breed:String,
    val subBreed:String,
    val name:String,
    val age: Int,
    val sex:String,
    val description:String,
    val weigth:Float,
    val location:String,
    val imgs:String,
    val owner:String,
    val favorite: Boolean,
    val adopted: Boolean
) fun PublicationEntity.toDomain() = Publication(
    id,
    breed,
    subBreed,
    name,
    age,
    sex,
    description,
    weigth,
    location,
    imgs,
    owner,
    favorite,
    adopted
)