package com.example.pokedex.ui.screens.details

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.enums.LoadStatus
import com.example.pokedex.data.models.pokemon.PokemonDetail
import com.example.pokedex.data.repositories.pokemon.PokemonRepository
import com.example.pokedex.data.repositories.pokemon.PokemonRepositoryImpl
import com.example.pokedex.di.NetworkModule
import kotlinx.coroutines.launch

class DetailsVM(
    private val application: Application,
    private val repository: PokemonRepository = PokemonRepositoryImpl(
        NetworkModule.apiService
    )
) :
    AndroidViewModel(application = application) {
    private val _pokemonDetail = mutableStateOf<PokemonDetail?>(null)
    val pokemonDetail: State<PokemonDetail?> = _pokemonDetail

    private val _loadStatus = mutableStateOf(LoadStatus.Initial)
    val loadStatus: State<LoadStatus> = _loadStatus

    private val _currentID = mutableStateOf<Int?>(null)
    val currentID: State<Int?> = _currentID


    fun loadPokemonDetail(id: Int) {
        _loadStatus.value = LoadStatus.Loading

        viewModelScope.launch {
            try {
                val response = repository.getPokemonDetail(
                   id = id
                )

                _pokemonDetail.value = response
                _currentID.value = response.id
                _loadStatus.value = LoadStatus.Success
            } catch (e: Exception) {
                println("Error: ${e.message}")
                _loadStatus.value = LoadStatus.Error
            }
        }
    }
}