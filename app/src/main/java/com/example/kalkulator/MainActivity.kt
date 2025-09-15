package com.example.kalkulator


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

import com.example.kalkulator.ui.theme.KalkulatorTheme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KalkulatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MathScreen(modifier = Modifier.padding(innerPadding))

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KalkulatorTheme {
        Greeting("Android")
    }
}

@Composable
fun NumberBox(
    value: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .aspectRatio(1f)
            .background(Color.Blue, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun EquationRow(
    first: Int,
    second: Int,
    answer: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,

    ) {
        NumberBox(first.toString(), modifier = Modifier.weight(1f))
        Operator("+")
        NumberBox(second.toString(), modifier = Modifier.weight(1f))
        Operator("=")
        NumberBox(answer, modifier = Modifier.weight(1f))
    }
}

@Composable
fun Operator(value: String) {
    Box(modifier = Modifier.size(32.dp), contentAlignment = Alignment.Center) {
        Text(value, style = MaterialTheme.typography.headlineSmall)
    }
}

@Composable
fun CalculatorPad (
    onNumberClick: (String) -> Unit,
) {
    val numbers = listOf(
        listOf("1", "2", "3"),
        listOf("4", "5", "6"),
        listOf("7", "8", "9"),
        listOf("", "0", "")
    )

    Column (
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(4.dp)
    ) {
        numbers.forEach { row ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                row.forEach { number ->
                    if (number.isEmpty()) {
                        Spacer(modifier = Modifier.weight(1f))
                    } else {
                        Button(
                            onClick = {onNumberClick(number)},
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(
                                text = number,
                                style = MaterialTheme.typography.headlineSmall)
                        }
                    }
                }
            }
        }
    }

}

@Composable
fun CalcButton(
    label: String,
    onClick: () -> Unit,
    color: Color,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = color
        )
    ) {
        Text(
            text = label,
            color = Color.White,
            style = MaterialTheme.typography.headlineSmall
        )

    }
}

@Composable
fun MathScreen(modifier: Modifier = Modifier) {
    var answer by remember { mutableStateOf("") }

    val firstNumber = 3
    val secondNumber = 5

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(50.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EquationRow(
            first = firstNumber,
            second = secondNumber,
            answer = answer,
            modifier = Modifier
        )
        CalcButton(
            label = "Submit",
            color = Color.Green,
            onClick = {},
        )
        CalculatorPad(
            onNumberClick = { number ->
                answer += number
            }
        )
        CalcButton(
            label = "Clear",
            color = Color.Red,
            onClick = {},
        )
    }
}

@Preview
@Composable
fun MathScreenPreview() {
    MathScreen()
}
