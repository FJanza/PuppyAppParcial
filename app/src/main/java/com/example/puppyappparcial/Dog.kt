package com.example.puppyappparcial

import com.google.gson.annotations.SerializedName

data class Dog(
    @SerializedName("name") val name: String,
    @SerializedName("age") val age: Int,
    @SerializedName("location") val location: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("weight") val weight: Int,
    @SerializedName("description") val description: String,
    @SerializedName("imageUrl") val imageUrl: List<String>,
    @SerializedName("imageUrlOwner") val imageUrlOwner: String,
    @SerializedName("ownerName") val ownerName: String,
    @SerializedName("ownerNumber") val ownerNumber: Int,
    @SerializedName("isFaved") val isFaved: Boolean,
    @SerializedName("isAdopted") val isAdopted: Boolean,

    )
