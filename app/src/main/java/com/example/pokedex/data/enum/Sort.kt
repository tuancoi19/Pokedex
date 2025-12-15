package com.example.pokedex.data.enum

enum class Sort {
    Number, Name;

    val title: String
        get() = when (this) {
            Number -> "Number"
            Name -> "Name"
        }
}