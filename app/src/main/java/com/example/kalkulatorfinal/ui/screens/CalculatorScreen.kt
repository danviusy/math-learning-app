package com.example.kalkulatorfinal.ui.screens

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    val score = calcUiState.value.score
    var roundIndex by remember { mutableStateOf(1) }
    var answer by remember { mutableStateOf("") }
    var correctGuess: Boolean? by remember { mutableStateOf(null) }
    var showAnswerDialog by remember { mutableStateOf(false) }

    Scaffold { innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
        ) {

            if (roundIndex >= noEquations) {
                navController.navigate("summary-screen")
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column (
                        modifier = Modifier
                            .background(
                                color = Color.Green,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Text("Runde: ${roundIndex} / ${noEquations.toString()}")
                        // Text("Score: ${score.toString()} / ${noEquations.toString()} ")
                    }
                    Button(
                        modifier = Modifier
                            .padding(8.dp),
                        colors = ButtonDefaults.buttonColors(Color.Red, Color.White),
                        onClick = { showDialog = true },
                        shape = RoundedCornerShape(8.dp),

                    ) {
                        Text("Avslutt spillet")
                    }
                }

            }




            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Equation(firstNumber, secondNumber, answer, modifier = Modifier)
            }



            if (showAnswerDialog == true) {
                if (correctGuess == true) {
                    Dialog(
                        onDismissRequest = {showAnswerDialog = false },
                        onConfirmation = {
                            showAnswerDialog = false
                        },
                        dialogTitle = "Correct!",
                        dialogText = "Correct!"
                    )
                } else {
                    Dialog(
                        onDismissRequest = {showAnswerDialog = false },
                        onConfirmation = {
                            showAnswerDialog = false
                        },
                        dialogTitle = "Wrong!",
                        dialogText = "Wrong, answer was ${viewModel.getAnswer()}"
                    )
                }

            }


            CalculatorPad(
                onNumberClick = { number ->
                answer += number
            },
                onDeleteClick = {
                    answer = ""
                },
                onSendClick = {
                    viewModel.checkAnswer(answer)
                    correctGuess = viewModel.answerCorrect(answer)
                    showAnswerDialog = true
                    answer = ""
                    roundIndex = roundIndex + 1
                },
                currentInput = answer

            )








            if (showDialog) {
                Dialog(
                    onDismissRequest = {showDialog = false },
                    onConfirmation = {
                        showDialog = false
                        viewModel.setInterruptStatus(true)
                        navController.navigate("start-screen")
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
        modifier = modifier
            .aspectRatio(1f)
            .background(Color.Blue, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }

}

@Composable
fun Operator(value: String) {
    Box(
        modifier = Modifier.size(32.dp), contentAlignment = Alignment.Center
        ) {
        Text(text = value, style = MaterialTheme.typography.headlineSmall)
    }
}
/*
@Composable
fun EquationRow(
    first: String,
    second: String,
    answer: String,
    modifier: Modifier = Modifier,

) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
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
} */

@Composable
fun Equation(
    first: String,
    second: String,
    answer: String,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .background(Color(0xFF2E7D32), RoundedCornerShape(8.dp))
            .padding(40.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = first + " + " +  second + " = ?",
            fontSize = 48.sp,
            style = MaterialTheme.typography.headlineMedium.copy(
                color = Color.White
            )
        )
        Text(
            modifier = Modifier.height(60.dp),
            text = answer,
            fontSize = 48.sp,
            style = MaterialTheme.typography.headlineMedium.copy(
                color = Color.White
            )
        )
    }
}


@Composable
fun CalculatorPad (
    onNumberClick: (String) -> Unit,
    onDeleteClick: () -> Unit,
    onSendClick: (String) -> Unit,
    currentInput: String
) {
    val numbers = listOf(
        listOf("1", "2", "3"),
        listOf("4", "5", "6"),
        listOf("7", "8", "9"),
        // listOf("", "0", "")
    )

    Column (
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        numbers.forEach { row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                row.forEach { number ->
                    if (number.isEmpty()) {
                        Spacer(modifier = Modifier.weight(1f))
                    } else {
                        Button(
                            onClick = {onNumberClick(number)},
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(text = number, style = MaterialTheme.typography.headlineSmall)

                        }
                    }
                }
            }
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { onDeleteClick() },
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(Color.Red)
            ) {
                Text("DEL", style = MaterialTheme.typography.headlineSmall)
            }

            Button(
                onClick = { onNumberClick("0") },
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("0", style = MaterialTheme.typography.headlineSmall)
            }

            Button(
                onClick = { onSendClick(currentInput) },
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(Color.Green)
            ) {
                Text("SEND", style = MaterialTheme.typography.headlineSmall)
            }
        }

    }

}




