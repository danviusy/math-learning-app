package com.example.kalkulatorfinal.ui.screens

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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kalkulatorfinal.ui.CalculatorViewModel
import com.example.kalkulatorfinal.ui.theme.Orange80

@Composable
fun About(navController: NavController, viewModel: CalculatorViewModel) {
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
            Box(
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
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "Spill",
                    tint = Color.White,
                    modifier = Modifier.size(64.dp)
                )
            }

            Box(
                modifier = Modifier
                    .padding(32.dp)
                    .background(
                        color = MaterialTheme.colorScheme.tertiary,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .border(
                        width = 2.dp,
                        color = Orange80,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(32.dp)

            ) {
                Text("Denne appen gjør læring av matematikk morsomt og enkelt for barn. " +
                        "Det er små og enkle utfordringer i spillet som barna kan bruke til å øve " +
                        "på addisjon. Man kan velge å gjøre 5, 10 eller 15 oppgaver om gangen - " +
                        "altså den egner seg for hvilken som helst konsentrasjonsevne.", color = MaterialTheme.colorScheme.onPrimary, fontSize = MaterialTheme.typography.titleLarge.fontSize)
            }

            Button(
                shape = RoundedCornerShape(16.dp),
                contentPadding = PaddingValues(32.dp),
                onClick = {navController.navigate("start-screen")} )
            {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Tilbake",
                    tint = Color.White,
                    modifier = Modifier.size(64.dp)
                )
            }
        }

    }
}