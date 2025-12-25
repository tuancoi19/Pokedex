package com.example.pokedex.data.models

import com.google.gson.annotations.SerializedName

data class ArrayResponse<T>(
    @SerializedName("count")
    val count: Int,

    @SerializedName("results")
    val results: List<T>
)
