package com.example.kalkulatorfinal.ui

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.application
import com.example.kalkulatorfinal.R
import com.example.kalkulatorfinal.data.CalculatorUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CalculatorViewModel(application: Application): AndroidViewModel(application) {

    private val _uiState = MutableStateFlow(CalculatorUiState())
    val uiState: StateFlow<CalculatorUiState> = _uiState.asStateFlow() // UI-state

    private val res = application.resources

    // Array til å hente inn tallene og svarene
    val firstArray = res.getStringArray(R.array.first_numbers)
    val secondArray = res.getStringArray(R.array.second_numbers)
    val answerArray = res.getStringArray(R.array.answers)

    // Array der tilfeldige tall fra firstArray og secondArray blir lagret, spill-array
    val firstNumbers: MutableList<String> = mutableListOf()
    val secondNumbers: MutableList<String> = mutableListOf()
    val answers: MutableList<String> = mutableListOf()

    // Holder kontroll på om spiller har avbrutt runden
    private val _interruptStatus = MutableStateFlow(false)


    // Indeks på hvilket regnestykket spilleren er på
    var currentIndex: Int = 0


    // Holder kontroll på brukte regnestykker, slik at samme regnestykke ikke kommer opp igjen
    private var usedEquationsIndex: MutableSet<Int> = mutableSetOf()

    // SharedPreferences som lager antall spørsmål per runde
    fun setPref(noEquations: Int) {
        val sharedPreferences: SharedPreferences = application.getSharedPreferences("My Preferences",
            android.content.Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("noEquations", noEquations)
        editor.apply()
    }

    fun getPref(): Int {
        val sharedPreferences: SharedPreferences = application.getSharedPreferences("My Preferences",
            android.content.Context.MODE_PRIVATE)
        return sharedPreferences.getInt("noEquations", 5)
    }


    // Setter opp en ny runde, men bygger på forrige runder
    fun resetGame() {
       createEquations(getPref())
        _uiState.value = CalculatorUiState(
            score = 0,
            currentFirstNumber = firstNumbers[currentIndex],
            currentSecondNumber = secondNumbers[currentIndex]
        )
    }

    // Avbryter en runde og resetter spill-arrays
    fun interruptRound() {
        firstNumbers.clear()
        secondNumbers.clear()
        answers.clear()
        usedEquationsIndex.clear()
        currentIndex = 0
    }

    // Lager spill-arrayene
    private fun createEquations(noEquations: Int) {
        for (i in 0..noEquations - 1) {
            val index = pickRandomIndex()
            firstNumbers.add(firstArray[index])
            secondNumbers.add(secondArray[index])
            answers.add(answerArray[index])
        }
    }


    // Velger en random index innenfor orginal-arrayene, men sjekker om den er brukt fra før
    private fun pickRandomIndex(): Int {
        val randomIndex = firstArray.indices.random()
        return if (usedEquationsIndex.contains(randomIndex)) {
            pickRandomIndex()
        } else {
            usedEquationsIndex.add(randomIndex)
            randomIndex
        }
    }

    // Sjekker om det er flere spørsmål igjen ved å sammenligne currentindex med lengden til arrayene
    fun noQuestionsLeft(): Int {
        val totalNoQuestions = firstArray.size
        return totalNoQuestions - currentIndex
    }


    // Sender inn et svar og progresserer runden
    fun checkAnswer(guess: String) {
        currentIndex++ // Øker indeksen
        // Oppdaterer UI-state variablene
        if (guess.equals(answers[currentIndex - 1])) {
            _uiState.value = CalculatorUiState(
                score = _uiState.value.score + 1,
                currentFirstNumber = firstNumbers[currentIndex],
                currentSecondNumber = secondNumbers[currentIndex],
            )
        } else {
            _uiState.value = CalculatorUiState(
                score = _uiState.value.score,
                currentFirstNumber = firstNumbers[currentIndex],
                currentSecondNumber = secondNumbers[currentIndex],
            )
        }
    }

    // Sjekker om et svar er riktig, returnerer en Boolean verdi uten å progressere runden
    fun answerCorrect(guess: String): Boolean {
        return if (guess.equals(getAnswer())) {
            true
        } else {
            false
        }
    }

    fun getAnswer() : String { // Henter inn svar fra arrayet
        return answers[currentIndex - 1]
    }

    fun setInterruptStatus(status: Boolean) {
        _interruptStatus.value = status
    }

    fun getInterruptStatus(): Boolean {
        return _interruptStatus.value
    }
}