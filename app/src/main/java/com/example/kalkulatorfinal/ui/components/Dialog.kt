package com.example.kalkulatorfinal.ui.components

import android.app.Dialog
import android.security.ConfirmationPrompt
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kalkulatorfinal.ui.theme.Orange80
import com.example.kalkulatorfinal.ui.theme.Typography

@Composable
fun Dialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: @Composable () -> Unit
) {
    AlertDialog(
        icon = {},
        containerColor = MaterialTheme.colorScheme.background,
        title = { Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            icon()
            Text(text = dialogTitle)
        } },
        text = {Text(text = dialogText, fontSize = 24.sp)},
        onDismissRequest = {onDismissRequest()},
        confirmButton =  {
            TextButton(
                onClick = {onConfirmation()}
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Ja",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(40.dp)
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = { onDismissRequest()}
            ) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Nei",
                    tint = Orange80,
                    modifier = Modifier.size(40.dp)
                )
            }
        },

    )
}

@Composable
fun AnswerDialog(
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: @Composable () -> Unit
) {
    AlertDialog(
        icon = {},
        containerColor = MaterialTheme.colorScheme.background,
        title = { Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            icon()
            Text(text = dialogTitle)
        } },
        text = {Text(text = dialogText)},
        onDismissRequest = { },
        confirmButton =  {
            TextButton(
                onClick = {onConfirmation()}
            ) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = "Ja",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    )
}