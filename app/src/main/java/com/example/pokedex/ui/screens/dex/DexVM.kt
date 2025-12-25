package com.example.pokedex.ui.screens.dex

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.data.enums.LoadStatus
import com.example.pokedex.data.enums.Sort
import com.example.pokedex.data.models.pokemon.Pokemon
import com.example.pokedex.data.repositories.pokemon.PokemonRepository
import com.example.pokedex.data.repositories.pokemon.PokemonRepositoryImpl
import com.example.pokedex.di.NetworkModule
import com.example.pokedex.utils.AppConstant
import kotlinx.coroutines.launch

class DexVM(
    private val application: Application,
    private val repository: PokemonRepository = PokemonRepositoryImpl(
        NetworkModule.apiService
    )
) :
    AndroidViewModel(application = application) {
    private val _searchText = mutableStateOf("")
    val searchText: State<String> = _searchText

    private val _sortType = mutableStateOf(Sort.Number)
    val sortType: State<Sort> = _sortType

    private val _pokemon = mutableStateListOf<Pokemon>()
    val pokemon: List<Pokemon> = _pokemon

    private val _loadStatus = mutableStateOf(LoadStatus.Initial)
    val loadStatus: State<LoadStatus> = _loadStatus

    private val _isLoadMore = mutableStateOf(false)
    val isLoadMore: State<Boolean> = _isLoadMore

    private var _offset: Int = 0
    private var _count: Int = 0

    fun setSearchText(text: String) {
        _searchText.value = text
    }

    fun toggleSortType() {
        _sortType.value = when (_sortType.value) {
            Sort.Number -> Sort.Name
            Sort.Name -> Sort.Number
        }
    }

    fun loadPokemon() {
        _offset = 0
        _count = 0
        _pokemon.clear()

        _loadStatus.value = LoadStatus.Loading

        viewModelScope.launch {
            try {
                val response = repository.getPokemon(
                    _offset,
                    AppConstant.PAGE_SIZE
                )

                _pokemon.addAll(response.results)
                _offset += AppConstant.PAGE_SIZE
                if (_count != response.count) _count = response.count
                _loadStatus.value = LoadStatus.Success
            } catch (e: Exception) {
                println("Error: ${e.message}")
                _loadStatus.value = LoadStatus.Error
            }
        }
    }

    fun loadMorePokemon() {
        if (_isLoadMore.value || loadStatus.value != LoadStatus.Success) return
        if (_count != 0 && _offset >= _count) return

        _isLoadMore.value = true

        viewModelScope.launch {
            try {
                val response = repository.getPokemon(
                    _offset,
                    AppConstant.PAGE_SIZE
                )
                _pokemon.addAll(response.results)
                _offset += AppConstant.PAGE_SIZE
                if (_count != response.count) _count = response.count
                _isLoadMore.value = false
            } catch (e: Exception) {
                println("Error: ${e.message}")
                _isLoadMore.value = false
            }
        }
    }
}