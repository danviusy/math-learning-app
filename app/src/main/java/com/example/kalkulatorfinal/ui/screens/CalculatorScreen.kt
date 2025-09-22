package com.example.kalkulatorfinal.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kalkulatorfinal.ui.CalculatorViewModel
import com.example.kalkulatorfinal.ui.components.Dialog


@Composable
fun CalculatorScreen(navController: NavController, viewModel: CalculatorViewModel) {
    var showDialog by remember { mutableStateOf(false) }
    val noEquations = viewModel.getPref()
    val calcUiState = viewModel.uiState.collectAsState()
    val firstNumber = calcUiState.value.currentFirstNumber
    val secondNumber = calcUiState.value.currentSecondNumber
    var answer by remember { mutableStateOf("") }

    Scaffold { innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
        ) {

            Text("Antall regnestykker:  ${noEquations.toString()}")

            EquationRow(firstNumber, secondNumber, answer, modifier = Modifier)

            CalculatorPad(onNumberClick = { number ->
                answer += number
            } )


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

@Composable
fun NumberBox(
    value: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
    ) {
        Text(
            text = value
        )
    }

}

@Composable
fun Operator(value: String) {
    Box(modifier = Modifier) {
        Text(text = value)
    }
}

@Composable
fun EquationRow(
    first: String,
    second: String,
    answer: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        NumberBox(
            value = first,
            modifier = Modifier
                .weight(1f)
        )
        Operator(value = "+")
        NumberBox(
            value = second,
            modifier = Modifier
                .weight(1f)
        )
        Operator(value = "=")
        NumberBox(
            value = answer,
            modifier = Modifier
                .weight(1f)
        )
    }
}

@Composable
fun CalculatorPad (
    onNumberClick: (String) -> Unit,
) {
    val numbers = listOf(
        listOf("1", "2", "3"),
        listOf("4", "5", "6"),
        listOf("7", "8", "9"),
        listOf("", "0", "")
    )

    Column (
        modifier = Modifier.padding(4.dp)
    ) {
        numbers.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                row.forEach { number ->
                    if (number.isEmpty()) {
                    } else {
                        Button(
                            onClick = {onNumberClick(number)},
                            modifier = Modifier
                        ) {
                            Text(text = number)
                        }
                    }
                }
            }
        }
    }

}