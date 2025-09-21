package com.example.kalkulatorfinal.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.kalkulatorfinal.ui.components.Dialog


@Composable
fun CalculatorScreen(navController: NavController) {
    var showDialog by remember { mutableStateOf(false) }
    Scaffold { innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
        ) {

            Text("Dette er spill-skjermen")
            Button(onClick = {navController.navigate("summary-screen")} ) {
                Text("Fullfør spillet")
            }
            Button(
                onClick = { showDialog = true }
            ) {
                Text("Avslutt spillet")
            }
            if (showDialog) {
                Dialog(
                    onDismissRequest = {showDialog = false },
                    onConfirmation = {
                        showDialog = false
                        navController.navigate("select-option")
                    },
                    dialogTitle = "Avslutte spillet",
                    dialogText = "Er du sikker på at du vil avslutte spillet?"
                )
            }
        }
    }
}