package com.example.pokedex.di

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokedex.ui.screens.dex.DexVM
import kotlin.jvm.java

class DexVMFactory(
    private val application: Application
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DexVM::class.java)) {
            @Suppress("UNCHECKED_CAST") return DexVM(application) as T
        }
        throw kotlin.IllegalArgumentException("Unknown ViewModel class: $modelClass")
    }
}
