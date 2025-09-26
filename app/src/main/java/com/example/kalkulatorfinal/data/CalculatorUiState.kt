package com.example.kalkulatorfinal.data

data class CalculatorUiState(
    val score: Int = 0, // Score er innsatt, men ikke implementert - kan bygges videre på
    val currentFirstNumber: String = "", // Første tall i regnestykket
    val currentSecondNumber: String = "" // Andre tall i regnestykket
)