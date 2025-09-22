package com.example.kalkulatorfinal.ui

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.application

class CalculatorViewModel(application: Application): AndroidViewModel(application) {
    fun setPref(noEquations: Int) {
        val sharedPreferences: SharedPreferences = application.getSharedPreferences("My Preferences",
            android.content.Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt("noEquations", noEquations)
        editor.apply()
    }

    fun getPref(): Int? {
        val sharedPreferences: SharedPreferences = application.getSharedPreferences("My Preferences",
            android.content.Context.MODE_PRIVATE)
        return sharedPreferences.getInt("noEquations", 5)
    }
}