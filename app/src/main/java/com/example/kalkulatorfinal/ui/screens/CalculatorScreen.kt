package com.example.kalkulatorfinal.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController


@Composable
fun CalculatorScreen(navController: NavController) {
    Scaffold { innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
        ) {
            Text("Dette er spill-skjermen")
            Button(onClick = {navController.navigate("summary-screen")} ) {
                Text("Fullfør spillet")
            }
        }
    }
}