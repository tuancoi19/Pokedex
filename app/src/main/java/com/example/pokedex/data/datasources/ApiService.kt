package com.example.pokedex.data.datasources

import com.example.pokedex.data.models.ArrayResponse
import com.example.pokedex.data.models.pokemon.Pokemon
import com.example.pokedex.data.models.pokemon.PokemonDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(ApiUrl.GET_POKEMON)
    suspend fun getPokemon(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): ArrayResponse<Pokemon>

    @GET(ApiUrl.GET_POKEMON_DETAIL)
    suspend fun getPokemonDetail(
        @Path("id") id: Int
    ): PokemonDetail
}