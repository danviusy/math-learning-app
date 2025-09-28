package com.example.kalkulatorfinal.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
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
import androidx.navigation.NavController
import com.example.kalkulatorfinal.R
import com.example.kalkulatorfinal.ui.CalculatorViewModel
import com.example.kalkulatorfinal.ui.components.AnswerDialog
import com.example.kalkulatorfinal.ui.theme.Orange80

@Composable
fun SummaryScreen(navController: NavController, viewModel: CalculatorViewModel) {
    val calcUiState = viewModel.uiState.collectAsState()
    val lastGuess = calcUiState.value.lastGuess
    val correctGuess = viewModel.answerCorrect(lastGuess)
    var showAnswerDialog by remember { mutableStateOf(true) }

    Scaffold { innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                32.dp,
                alignment = Alignment.Top
            )
        ) {

            // Viser tilbake melding på sist input
            if (showAnswerDialog) {
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


            Image( // Logo
                painter = painterResource(id = R.drawable.matte_icon),
                contentDescription = "Logo",
                modifier = Modifier.padding(16.dp)
            )

            Box( // Text-boks
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.tertiary,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .border(
                        width = 2.dp,
                        color = Orange80,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(vertical = 32.dp, horizontal = 64.dp)

            ) {
                Text(stringResource(R.string.game_done), color = MaterialTheme.colorScheme.onPrimary, fontSize = MaterialTheme.typography.titleLarge.fontSize)
            }
            Button( // Returnerer tilbake til start-skjermen
                shape = RoundedCornerShape(16.dp),
                contentPadding = PaddingValues(32.dp),
                onClick = {navController.navigate("start-screen")} )
            {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "Spill igjen",
                    tint = Color.White,
                    modifier = Modifier.size(64.dp)
                )
            }
        }
    }
}