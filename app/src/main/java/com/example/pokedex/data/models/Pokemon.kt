package com.example.pokedex.data.models

import com.example.pokedex.utils.formatNumber
import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("name") val name: String,

    @SerializedName("url") private val url: String
) {
    private val id: Int
        get() = url.trimEnd('/').substringAfterLast('/').toInt()

    val number: String
        get() = id.formatNumber()

    val imageUrl: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
}
