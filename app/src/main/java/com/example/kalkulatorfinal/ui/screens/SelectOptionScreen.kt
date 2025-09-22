package com.example.kalkulatorfinal.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kalkulatorfinal.ui.CalculatorViewModel

@Composable
fun SelectOptionScreen(navController: NavController, viewModel: CalculatorViewModel) {
    var noEquations by remember { mutableIntStateOf(5) }


    Scaffold { innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Text("Velg antall regnestykker")
            Button(onClick = {
                viewModel.setPref(5)
                viewModel.resetGame()
                navController.navigate("game-screen")
            } ) {
                Text("5")
            }
            Button(onClick = {
                viewModel.setPref(10)
                viewModel.resetGame()
                navController.navigate("game-screen")
            } ) {
                Text("10")
            }
            Button(onClick = {
                viewModel.setPref(15)
                viewModel.resetGame()
                navController.navigate("game-screen")
            } ) {
                Text("15")
            }
        }
    }
}