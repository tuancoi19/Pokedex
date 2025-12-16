package com.example.pokedex.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pokedex.di.DexVMFactory
import com.example.pokedex.ui.screens.dex.DexScreen
import com.example.pokedex.ui.screens.dex.DexVM
import com.yamaha.meisterstudio.ui.navigation.Routes

@Composable
fun AppNavigation(
    startDestination: String = Routes.Dex.route,
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Routes.Dex.route) { backStackEntry ->
            val viewModel: DexVM = viewModel(
                backStackEntry,
                factory = DexVMFactory(LocalContext.current.applicationContext as Application),
            )
            DexScreen(
                viewModel = viewModel,
            )
        }
    }
}