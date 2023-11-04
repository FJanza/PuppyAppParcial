package com.example.puppyappparcial.data.model

import com.google.gson.annotations.SerializedName

data class BreedModelResponse(
    @SerializedName("message") var breeds: List<String>,
    @SerializedName("status") var status: String
)
