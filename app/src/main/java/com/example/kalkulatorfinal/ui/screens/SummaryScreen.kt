package com.example.kalkulatorfinal.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.kalkulatorfinal.ui.CalculatorViewModel

@Composable
fun SummaryScreen(navController: NavController, viewModel: CalculatorViewModel) {
    Scaffold { innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Text("Du har fullført spillet!")
            Button(onClick = {navController.navigate("select-option")} ) {
                Text("Spill igjen?")
            }
        }
    }
}