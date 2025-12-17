package com.example.pokedex.ui.screens.dex

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.example.pokedex.data.enums.Sort

class DexVM(private val application: Application) :
    AndroidViewModel(application = application) {
    private val _searchText = mutableStateOf("")
    val searchText: State<String> = _searchText

    private val _sortType = mutableStateOf(Sort.Number)
    val sortType: State<Sort> = _sortType

    fun setSearchText(text: String) {
        _searchText.value = text
    }

    fun toggleSortType() {
        _sortType.value = when (_sortType.value) {
            Sort.Number -> Sort.Name
            Sort.Name -> Sort.Number
        }
    }
}