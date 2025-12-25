package com.example.pokedex.data.enums

enum class Error {
    error, empty;

    val message: String
        get() = when (this) {
            error -> "An unknown error occurred"
            empty -> "No data"
        }
}