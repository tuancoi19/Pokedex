package com.example.pokedex.data.repositories.pokemon

import com.example.pokedex.data.models.Pokemon
import com.example.pokedex.data.models.PokemonDetail

interface PokemonRepository {
    suspend fun getPokemon(
        offset: Int,
        limit: Int
    ): List<Pokemon>

    suspend fun getPokemonDetail(id: Int): PokemonDetail
}