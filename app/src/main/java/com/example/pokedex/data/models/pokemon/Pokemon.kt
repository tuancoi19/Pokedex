package com.example.pokedex.data.models.pokemon

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("name") val name: String,

    @SerializedName("url") private val url: String
) {
    val id: Int
        get() = url.trimEnd('/').substringAfterLast('/').toInt()

    val imageUrl: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
}
