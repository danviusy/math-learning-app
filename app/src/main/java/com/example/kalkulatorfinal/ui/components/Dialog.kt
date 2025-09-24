package com.example.kalkulatorfinal.ui.components

import android.app.Dialog
import android.security.ConfirmationPrompt
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun Dialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    // icon
) {
    AlertDialog(
        icon = {},
        title = { Text(text = dialogTitle) },
        text = {Text(text = dialogText)},
        onDismissRequest = {onDismissRequest()},
        confirmButton =  {
            TextButton(
                onClick = {onConfirmation()}
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = { onDismissRequest()}
            ) {
                Text("Dismiss")
            }
        },

    )
}