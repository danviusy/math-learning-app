package com.example.kalkulatorfinal.ui

import android.app.Application
import android.content.Context
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
    val uiState: StateFlow<CalculatorUiState> = _uiState.asStateFlow()

    private val res = application.resources

    val firstArray = res.getStringArray(R.array.first_numbers)
    val secondArray = res.getStringArray(R.array.second_numbers)
    val answerArray = res.getStringArray(R.array.answers)
    val firstNumbers: MutableList<String> = mutableListOf()
    val secondNumbers: MutableList<String> = mutableListOf()
    val answers: MutableList<String> = mutableListOf()
    private val _interruptStatus = MutableStateFlow(false)
    val interruptStatus: StateFlow<Boolean> = _interruptStatus.asStateFlow()

    var checkPointIndex = 0

    var currentIndex: Int = 0


    // and then answers
    private var usedEquationsIndex: MutableSet<Int> = mutableSetOf()
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

    fun resetGame() {
        // firstNumbers.clear()
        // secondNumbers.clear()
        // usedEquationsIndex.clear()
        // currentIndex = 0
       createEquations(getPref())
        _uiState.value = CalculatorUiState(
            score = 0,
            currentFirstNumber = firstNumbers[currentIndex],
            currentSecondNumber = secondNumbers[currentIndex]
        )
    }

    fun interruptRound() {
        firstNumbers.clear()
        secondNumbers.clear()
        answers.clear()
        usedEquationsIndex.clear()
        currentIndex = 0
    }

    private fun createEquations(noEquations: Int) {
        for (i in 0..noEquations - 1) {
            val index = pickRandomIndex()
            firstNumbers.add(firstArray[index])
            secondNumbers.add(secondArray[index])
            answers.add(answerArray[index])
        }
    }

    private fun pickRandomIndex(): Int {
        val randomIndex = firstArray.indices.random()
        return if (usedEquationsIndex.contains(randomIndex)) {
            pickRandomIndex()
        } else {
            usedEquationsIndex.add(randomIndex)
            randomIndex
        }
    }

    fun outOfQuestions(): Boolean {
        return currentIndex >= (firstArray.size - 1)
    }

    fun noQuestionsLeft(): Int {
        val totalNoQuestions = firstArray.size
        return totalNoQuestions - currentIndex
    }


    fun checkAnswer(guess: String) {
        currentIndex++
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

    fun answerCorrect(guess: String): Boolean {
        return if (guess.equals(answers[currentIndex - 1])) {
            true
        } else {
            false
        }
    }


    fun getAnswer() : String {
        return answers[currentIndex - 1]
    }

    fun setInterruptStatus(status: Boolean) {
        _interruptStatus.value = status
    }

    fun getInterruptStatus(): Boolean {
        return _interruptStatus.value
    }
}