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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kalkulatorfinal.R
import com.example.kalkulatorfinal.ui.theme.Orange80

@Composable
fun About(navController: NavController) {
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
            Image( // Logo
                painter = painterResource(id = R.drawable.matte_icon),
                contentDescription = "Logo",
                modifier = Modifier.padding(8.dp)
            )

            Box( // Text-boks om appen
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
                Text(stringResource(R.string.about_app), color = MaterialTheme.colorScheme.onPrimary, fontSize = MaterialTheme.typography.titleLarge.fontSize)
            }

            Button( // Navigerer tilbake til start-screen
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