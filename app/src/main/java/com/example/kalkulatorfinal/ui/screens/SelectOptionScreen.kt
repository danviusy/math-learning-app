package com.example.kalkulatorfinal.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kalkulatorfinal.ui.CalculatorViewModel

@Composable
fun SelectOptionScreen(navController: NavController, viewModel: CalculatorViewModel) {
    var noEquations by remember { mutableIntStateOf(5) }
    val interruptStatus = viewModel.getInterruptStatus()

    if (interruptStatus) {
        viewModel.setInterruptStatus(true)
        viewModel.interruptRound()
    }

    Scaffold { innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                16.dp,
                alignment = Alignment.CenterVertically
            )
        ) {
            Text(viewModel.noQuestionsLeft().toString())
            if (viewModel.noQuestionsLeft() >= 5) {
                Text("Gjenstående antall regnestykker: ${viewModel.noQuestionsLeft().toString()}")
                Text("Velg antall regnestykker")
                Row (
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        // modifier = Modifier.padding(32.dp),
                        contentPadding = PaddingValues(32.dp),
                        onClick = {
                            viewModel.setPref(5)
                            viewModel.resetGame()
                            navController.navigate("game-screen")
                        } ) {
                        Text("5 ")
                    }
                    if (viewModel.noQuestionsLeft() >= 10) {
                        Button(
                            contentPadding = PaddingValues(32.dp),
                            onClick = {
                            viewModel.setPref(10)
                            viewModel.resetGame()
                            navController.navigate("game-screen")
                        }) {
                            Text("10")
                        }
                        if (viewModel.noQuestionsLeft() >= 15) {
                            Button(
                                contentPadding = PaddingValues(32.dp),
                                onClick = {
                                viewModel.setPref(15)
                                viewModel.resetGame()
                                navController.navigate("game-screen")
                            }) {
                                Text("15")
                            }
                        }
                    }
                }
            }
        }
    }
}