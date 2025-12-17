package com.example.pokedex.data.models

import com.google.gson.annotations.SerializedName
import com.example.pokedex.data.enums.Stat
import com.example.pokedex.utils.formatNumber

data class PokemonDetail(
    @SerializedName("name")
    val name: String,

    @SerializedName("id")
    private val id: Int,

    @SerializedName("sprites")
    private val sprites: Sprites,

    @SerializedName("types")
    private val types: List<Types>,

    @SerializedName("height")
    val height: Int,

    @SerializedName("weight")
    val weight: Int,

    @SerializedName("moves")
    val moves: List<Moves>,

    @SerializedName("stats")
    val stats: List<Stats>
) {
    val number: String
        get() = id.formatNumber()
}

data class Sprites(
    @SerializedName("front_default")
    val frontDefault: String,
)

data class Types(
    @SerializedName("type")
    val type: Type
)

data class Type(
    @SerializedName("name")
    val name: String
)

data class Moves(
    @SerializedName("moves")
    val move: Move
)

data class Move(
    @SerializedName("name")
    val name: String
)

data class Stats(
    @SerializedName("base_stat")
    private val baseStat: Int,

    @SerializedName("stat")
    val stat: Stat
) {
    val index: String
        get() = baseStat.formatNumber(false)
}

data class Stat(
    @SerializedName("name")
    val name: String
) {
    val type: Stat
        get() = Stat.fromValue(name)
}
