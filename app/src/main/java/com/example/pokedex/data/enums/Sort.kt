package com.example.pokedex.data.enums

import com.example.pokedex.R

enum class Sort {
    Number, Name;

    val title: String
        get() = when (this) {
            Number -> "Number"
            Name -> "Name"
        }

    val icon: Int
        get() = when (this) {
            Number -> R.drawable.ic_number
            Name -> R.drawable.ic_name
        }
}