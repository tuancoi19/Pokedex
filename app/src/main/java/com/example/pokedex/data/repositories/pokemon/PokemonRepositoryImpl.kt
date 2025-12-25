package com.example.pokedex.data.repositories.pokemon

import com.example.pokedex.data.datasources.ApiService
import com.example.pokedex.data.models.ArrayResponse
import com.example.pokedex.data.models.pokemon.Pokemon
import com.example.pokedex.data.models.pokemon.PokemonDetail

class PokemonRepositoryImpl(private val api: ApiService) : PokemonRepository {
    override suspend fun getPokemon(
        offset: Int,
        limit: Int
    ): ArrayResponse<Pokemon> {
        return api.getPokemon(offset, limit)
    }

    override suspend fun getPokemonDetail(id: Int): PokemonDetail {
        return api.getPokemonDetail(id)
    }
}