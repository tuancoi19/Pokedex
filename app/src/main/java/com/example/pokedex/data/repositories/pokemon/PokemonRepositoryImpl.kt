package com.example.pokedex.data.repositories.pokemon

import com.example.pokedex.data.datasources.ApiService
import com.example.pokedex.data.models.Pokemon
import com.example.pokedex.data.models.PokemonDetail

class PokemonRepositoryImpl(private val api: ApiService) : PokemonRepository {
    override suspend fun getPokemon(
        offset: Int,
        limit: Int
    ): List<Pokemon> {
        return api.getPokemon(offset, limit)
    }

    override suspend fun getPokemonDetail(id: Int): PokemonDetail {
        return api.getPokemonDetail(id)
    }
}