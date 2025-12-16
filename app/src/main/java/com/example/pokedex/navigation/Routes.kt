package com.yamaha.meisterstudio.ui.navigation

sealed class Routes(val route: String) {
    object Dex : Routes("dex")

}