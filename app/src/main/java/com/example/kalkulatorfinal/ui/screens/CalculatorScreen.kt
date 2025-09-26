package com.example.kalkulatorfinal.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kalkulatorfinal.ui.CalculatorViewModel
import com.example.kalkulatorfinal.ui.components.Dialog
import com.example.kalkulatorfinal.ui.theme.Orange80
import com.example.kalkulatorfinal.R
import com.example.kalkulatorfinal.ui.components.AnswerDialog


@Composable
fun CalculatorScreen(navController: NavController, viewModel: CalculatorViewModel) {
    var showDialog by remember { mutableStateOf(false) }
    val noEquations = viewModel.getPref()
    val calcUiState = viewModel.uiState.collectAsState()
    val firstNumber = calcUiState.value.currentFirstNumber // Henter første tall fra regnestykket
    val secondNumber = calcUiState.value.currentSecondNumber // Henter andre tall fra regnestykket
    var roundIndex by remember { mutableStateOf(1) } // Holder kontroll på hvor mange spørsmål som er gjort ila. en runde
    var answer by remember { mutableStateOf("") } // Spiller-input skrives inn hit
    var correctGuess: Boolean? by remember { mutableStateOf(null) } // Sjekker om svar er riktig eller feil
    var showAnswerDialog by remember { mutableStateOf(false) }

    Scaffold { innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
        ) {

            if (roundIndex >= noEquations) { // Når alle spørsmål ila. en runde er gjort
                navController.navigate("summary-screen") // Navigerer til slutt-screenen
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
                    Box(
                        modifier = Modifier.size(60.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Image( // Logo
                            painter = painterResource(id = R.drawable.matte_icon),
                            contentDescription = "Logo",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    // Antall spørsmål og total antall runder
                    Text("${roundIndex} / ${noEquations.toString()}", fontSize = 24.sp)

                    Button( // Avbryt runde-knapp
                        modifier = Modifier,
                        colors = ButtonDefaults.buttonColors(Orange80, Color.White),
                        onClick = { showDialog = true },
                        shape = RoundedCornerShape(50),

                    ) {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Hjem",
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }

            }

            Box( // Regnestykke
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                contentAlignment = Alignment.Center
            ) {
                Equation(firstNumber, secondNumber, answer, modifier = Modifier)
            }


            if (showAnswerDialog == true) {
                if (correctGuess == true) {
                    AnswerDialog( // Oppstår når svaret er riktig
                        icon = {
                            Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = "Riktig",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(72.dp)
                        )},
                        onConfirmation = {
                            showAnswerDialog = false
                        },
                        dialogTitle = "Riktig!",
                        dialogText = ""
                    )

                } else { // Oppstår når svaret er feil
                    AnswerDialog(
                        icon = {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = "Feil",
                                tint = Orange80,
                                modifier = Modifier.size(72.dp)
                            )},
                        onConfirmation = {
                            showAnswerDialog = false
                        },
                        dialogTitle = "Feil!",
                        dialogText = "Svaret var ${viewModel.getAnswer()}"
                    )
                }

            }

            CalculatorPad( // Kalkulator-pad
                onNumberClick = { number ->
                answer += number // Legger spiller-input i variablen answer
            },
                onDeleteClick = {
                    answer = ""
                },
                onSendClick = {
                    viewModel.checkAnswer(answer) // Sjekker om svaret er riktig
                    correctGuess = viewModel.answerCorrect(answer)
                    showAnswerDialog = true
                    answer = ""
                    roundIndex = roundIndex + 1 // Går til neste runde
                },
                currentInput = answer

            )

            if (showDialog) {
                Dialog(
                    onDismissRequest = {showDialog = false },
                    onConfirmation = {
                        showDialog = false
                        viewModel.setInterruptStatus(true) // Avbryter spill
                        navController.navigate("start-screen")
                    },
                    dialogTitle = stringResource(R.string.end_game),
                    dialogText = stringResource(R.string.end_game_confirmation),
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Hjem",
                            tint = Orange80,
                            modifier = Modifier.size(72.dp)
                        )
                    }
                )
            }
        }
    }
}

// Regnestykket
@Composable
fun Equation(
    first: String,
    second: String,
    answer: String,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.tertiary, shape = RoundedCornerShape(8.dp))
            .border(width = 2.dp, color = Orange80, shape = RoundedCornerShape(8.dp))
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
        listOf("7", "8", "9")
    )

    Column (
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        // Iterer gjennom hver rad i listen
        numbers.forEach { row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                row.forEach { number -> // Iterer gjennom hvert nummer i listen
                    if (number.isEmpty()) {
                        Spacer(modifier = Modifier.weight(1f))
                    } else {
                        NumberButton(
                            number = number,
                            onNumberClick = { onNumberClick(number) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        // Siste rad i kalkulator-paden: CLEAR, 0 og SEND
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Button( // CLEAR-knapp som sletter spillet-input
                onClick = { onDeleteClick() },
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(Orange80)
            ) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Slett",
                    tint = Color.White,
                    modifier = Modifier.size(72.dp)
                )
            }
            NumberButton( // 0-knapp
                number = "0",
                onNumberClick = { onNumberClick("0") },
                modifier = Modifier.weight(1f)
            )

            Button( // Sender input for å sjekke om svaret er riktig
                onClick = { onSendClick(currentInput) },
                modifier = Modifier
                    .weight(1f)
                    .aspectRatio(1f),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
            ) {

                Icon(
                    imageVector = Icons.Filled.Send,
                    contentDescription = "Send",
                    tint = Color.White,
                    modifier = Modifier.size(56.dp)
                )
            }
        }

    }
}

@Composable
fun NumberButton( // Hver tall-knapp på kalkulator-paden
    number: String,
    onNumberClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {onNumberClick(number)},
        modifier = modifier.aspectRatio(1f),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(text = number, fontSize = 32.sp)
    }
}




