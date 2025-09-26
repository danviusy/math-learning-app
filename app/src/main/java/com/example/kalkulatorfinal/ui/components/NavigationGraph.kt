package com.example.kalkulatorfinal.ui.components

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kalkulatorfinal.ui.CalculatorViewModel
import com.example.kalkulatorfinal.ui.screens.About
import com.example.kalkulatorfinal.ui.screens.CalculatorScreen
import com.example.kalkulatorfinal.ui.screens.SelectOptionScreen
import com.example.kalkulatorfinal.ui.screens.StartScreen
import com.example.kalkulatorfinal.ui.screens.SummaryScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    val calculatorViewModel: CalculatorViewModel = viewModel()


    NavHost(navController = navController, startDestination = "start-screen") { // Definerer hvor appen starter
        composable("start-screen") { StartScreen(navController = navController, calculatorViewModel) }
        composable("select-option") { SelectOptionScreen(navController = navController, calculatorViewModel) }
        composable("game-screen") { CalculatorScreen(navController = navController, calculatorViewModel) }
        composable("summary-screen") { SummaryScreen(navController = navController) }
        composable("about-screen") { About(navController = navController) }
    }
}