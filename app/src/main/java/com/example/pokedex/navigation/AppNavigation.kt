package com.example.pokedex.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pokedex.di.DetailsVMFactory
import com.example.pokedex.di.DexVMFactory
import com.example.pokedex.ui.screens.details.DetailsScreen
import com.example.pokedex.ui.screens.details.DetailsVM
import com.example.pokedex.ui.screens.dex.DexScreen
import com.example.pokedex.ui.screens.dex.DexVM

@Composable
fun AppNavigation(
    startDestination: String = Routes.Dex.route,
) {
    val navController = rememberNavController()

    @Composable
    fun application(): Application =
        LocalContext.current.applicationContext as Application

    fun idNavArgument() = navArgument("id") {
        type = NavType.IntType
        defaultValue = 1
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Routes.Dex.route) { backStackEntry ->
            val viewModel: DexVM = viewModel(
                backStackEntry,
                factory = DexVMFactory(application()),
            )
            DexScreen(
                viewModel = viewModel,
                onNavigateToDetail = { id ->
                    navController.navigate(
                        Routes.Details.createRoute(id)
                    )
                },
            )
        }

        composable(
            Routes.Details.fullRoute,
            arguments = listOf(idNavArgument())
        ) { backStackEntry ->
            val id =
                backStackEntry.arguments?.getInt(Routes.Details.ARG_ID) ?: 1

            val viewModel: DetailsVM = viewModel(
                backStackEntry,
                factory = DetailsVMFactory(application()),
            )
            DetailsScreen(
                viewModel = viewModel,
                id = id,
                onBack = navController::popBackStack
            )
        }
    }
}