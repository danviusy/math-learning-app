package com.example.kalkulatorfinal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kalkulatorfinal.ui.CalculatorViewModel
import com.example.kalkulatorfinal.ui.components.Dialog
import com.example.kalkulatorfinal.ui.components.NavigationGraph
import com.example.kalkulatorfinal.ui.screens.About
import com.example.kalkulatorfinal.ui.screens.CalculatorScreen
import com.example.kalkulatorfinal.ui.screens.SelectOptionScreen
import com.example.kalkulatorfinal.ui.screens.StartScreen
import com.example.kalkulatorfinal.ui.screens.SummaryScreen
import com.example.kalkulatorfinal.ui.theme.KalkulatorFinalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KalkulatorFinalTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GameScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun GameScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavigationGraph(navController = navController)
}
