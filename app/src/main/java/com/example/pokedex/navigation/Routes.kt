package com.example.pokedex.navigation

sealed class Routes(val route: String) {
    object Dex : Routes("dex")
    object Details : Routes("details") {
        const val ARG_ID = "id"
        val fullRoute = "$route?$ARG_ID={$ARG_ID}"

        fun createRoute(id: Int) = "$route?$ARG_ID=$id"
    }
}