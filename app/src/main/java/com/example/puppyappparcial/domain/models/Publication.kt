package com.example.puppyappparcial.domain.models

import com.example.puppyappparcial.data.database.entities.PublicationEntity


data class Publication(
    val id : Int,
    val breed:String,
    val subBreed:String?,
    val name:String,
    val age: Int,
    val sex:String,
    val description:String,
    val weight:Float,
    val location:String,
    val imgs:String,
    var owner:String,
    val ownerImgUrl: String,
    val ownerNumber: Int,
    val favorite: Boolean,
    var adopted: Boolean,
    val checked: Boolean
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
    ownerImgUrl,
    ownerNumber,
    favorite,
    adopted,
    checked
)
