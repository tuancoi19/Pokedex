package com.example.pokedex.navigation

sealed class Routes(val route: String) {
    object Dex : Routes("dex")
}