package com.example.pokedex.data.models.pokemon

import com.google.gson.annotations.SerializedName
import com.example.pokedex.data.enums.BaseStat
import com.example.pokedex.data.enums.TypeEnum
import com.example.pokedex.utils.formatNumber

data class PokemonDetail(
    @SerializedName("name")
    val name: String,

    @SerializedName("id")
    private val id: Int,

    @SerializedName("sprites")
    private val sprites: Sprites,

    @SerializedName("types")
    val types: List<Types>,

    @SerializedName("height")
    val height: Int,

    @SerializedName("weight")
    val weight: Int,

    @SerializedName("moves")
    val moves: List<Move>,

    @SerializedName("stats")
    val stats: List<Stats>
) {
    val number: String
        get() = id.formatNumber()

    val imageUrl: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
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
) {
    val typeEnum: TypeEnum
        get() = TypeEnum.fromValue(name)
}

data class Move(
    @SerializedName("move")
    val move: MoveEntity
)

data class MoveEntity(
    @SerializedName("name")
    val name: String
)

data class Stats(
    @SerializedName("base_stat")
    val baseStat: Int,

    @SerializedName("stat")
    val stat: Stat
)

data class Stat(
    @SerializedName("name")
    val name: String
) {
    val type: BaseStat
        get() = BaseStat.fromValue(name)
}
