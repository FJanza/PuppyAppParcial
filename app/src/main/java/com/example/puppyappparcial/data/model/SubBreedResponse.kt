package com.example.puppyappparcial.data.model

import com.google.gson.annotations.SerializedName

data class SubBreedResponse(
    @SerializedName("message") var subBreeds: List<String>,
    @SerializedName("status") var status: String
)