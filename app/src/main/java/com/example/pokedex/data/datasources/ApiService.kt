package com.example.pokedex.data.datasources

import com.example.pokedex.data.models.Pokemon
import com.example.pokedex.data.models.PokemonDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(ApiUrl.GET_POKEMON)
    suspend fun getPokemon(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): List<Pokemon>

    @GET(ApiUrl.GET_POKEMON_DETAIL)
    suspend fun getPokemonDetail(
        @Path("id") id: Int
    ): PokemonDetail
}