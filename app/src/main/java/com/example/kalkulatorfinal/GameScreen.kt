package com.example.kalkulatorfinal

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable

enum class GameScreen(val title: String) {
    Start(title = "Choose gamemode"),
    Game(title = "Game"),
    Summary(title = "Summary")
}

@Composable
