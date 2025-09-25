package com.example.kalkulatorfinal.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kalkulatorfinal.ui.CalculatorViewModel
import com.example.kalkulatorfinal.ui.components.Dialog

@Composable
fun StartScreen(navController: NavController, viewModel: CalculatorViewModel) {
    var showDialog by remember { mutableStateOf(false) }
    val interruptStatus = viewModel.getInterruptStatus()

    if (interruptStatus) {
        viewModel.setInterruptStatus(true)
        viewModel.interruptRound()
    }

    Scaffold { innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                16.dp,
                alignment = Alignment.CenterVertically
            )
        ) {
            if (showDialog) {
                Dialog(
                    onDismissRequest = { showDialog = false },
                    onConfirmation = {
                        showDialog = false
                    },
                    dialogTitle = "Ingen flere spørsmål",
                    dialogText = "Du har gått tom for spørsmål!"
                )
            }


            Text(viewModel.noQuestionsLeft().toString())

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                contentPadding = PaddingValues(32.dp),
                colors = ButtonDefaults.buttonColors(Color.Green),
                onClick = {
                if (viewModel.noQuestionsLeft() <= 5) {
                    showDialog = true
                } else {
                    navController.navigate("select-option")
                }
            }) {
                Text("Spill")
            }
            Button(
                modifier = Modifier.padding(32.dp),
                contentPadding = PaddingValues(16.dp),
                onClick = {
                    navController.navigate("about-screen")
            }) {
                Text("Om spillet")
            }
        }
    }
}