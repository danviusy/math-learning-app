package com.example.kalkulatorfinal.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
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
@Composable
fun StartScreen(navController: NavController, viewModel: CalculatorViewModel) {
    var showDialog by remember { mutableStateOf(false) }
    val interruptStatus = viewModel.getInterruptStatus() // Sjekker om bruker har avbrutt spill fra før av

    if (interruptStatus) {
        viewModel.setInterruptStatus(false)
        viewModel.interruptRound() // "Resetter" spillet
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
                AnswerDialog( // Oppstår når det ikke er flere spørsmål igjen
                    onConfirmation = {
                        showDialog = false
                    },
                    dialogTitle = stringResource(R.string.no_questions_left),
                    dialogText = " ",
                    icon = {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = "Stjerne",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(72.dp)
                        )
                    }
                )
            }

            Image( // Logo
                painter = painterResource(id = R.drawable.matte_icon),
                contentDescription = "Logo",
                modifier = Modifier.padding(32.dp)
            )

            Button( // Navigerer til select-option
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                contentPadding = PaddingValues(32.dp),
                shape = RoundedCornerShape(32.dp),
                onClick = {
                if (viewModel.noQuestionsLeft() <= 5) {
                    showDialog = true
                } else {
                    navController.navigate("select-option")
                }
            }) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = "Spill",
                    tint = Color.White,
                    modifier = Modifier.size(64.dp)
                )
            }
            Button( // Navigerer til "om"-siden
                modifier = Modifier.padding(32.dp),
                contentPadding = PaddingValues(32.dp),
                shape = RoundedCornerShape(32.dp),
                onClick = {
                    navController.navigate("about-screen")
            }) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "Spill",
                    tint = Color.White,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}