package com.example.pokedex.di

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokedex.ui.screens.details.DetailsVM
import com.example.pokedex.ui.screens.dex.DexVM

class DetailsVMFactory(
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsVM::class.java)) {
            @Suppress("UNCHECKED_CAST") return DetailsVM(application) as T
        }
        throw kotlin.IllegalArgumentException("Unknown ViewModel class: $modelClass")
    }
}