package com.example.perfil.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.perfil.ui.screens.HomeScreen
import com.example.perfil.ui.screens.DetailsScreen
import com.tu.paquete.viewmodel.ProfileViewModel

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Details : Routes("details")
}

@Composable
fun AppNavigation(viewModel: ProfileViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            HomeScreen(navController, viewModel)
        }
        composable(Routes.Details.route) {
            DetailsScreen(navController, viewModel)
        }
    }
}