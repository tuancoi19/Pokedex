package com.example.pokedex.data.repositories.pokemon

import com.example.pokedex.data.models.ArrayResponse
import com.example.pokedex.data.models.pokemon.Pokemon
import com.example.pokedex.data.models.pokemon.PokemonDetail

interface PokemonRepository {
    suspend fun getPokemon(
        offset: Int,
        limit: Int
    ): ArrayResponse<Pokemon>

    suspend fun getPokemonDetail(id: Int): PokemonDetail
}