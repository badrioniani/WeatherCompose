package com.example.weathercompose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weathercompose.presentation.MainActivity
import com.example.weathercompose.presentation.WeatherViewModel
import com.example.weathercompose.presentation.detail.DetailScreen
import com.example.weathercompose.presentation.forcast.ForecastScreen

@Composable
fun AppNavigation(navController: NavHostController,viewModel: WeatherViewModel) {
    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            ForecastScreen(navController,viewModel)
        }
        composable(
            "details/{weatherData}",
            arguments = listOf(navArgument("weatherData") { type = NavType.StringType })
        ) { backStackEntry ->
            DetailScreen(backStackEntry)
        }
    }
}