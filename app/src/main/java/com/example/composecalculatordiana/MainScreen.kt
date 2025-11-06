package com.example.composecalculatordiana

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun MainScreen() {
    val logic = remember { CalculatorLogic() }
    var input by remember { mutableStateOf("0") }
    var operator by remember { mutableStateOf<String?>(null) }
    var firstNumber by remember { mutableStateOf<Double?>(null) }

    val beigeBackground = Color(0xFFF7F3E9)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(beigeBackground)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CalculatorDisplay(input)

            val buttons = listOf(
                listOf("7", "8", "9", "/"),
                listOf("4", "5", "6", "*"),
                listOf("1", "2", "3", "-"),
                listOf("0", ".", "C", "+"),
                listOf("=")
            )

            buttons.forEach { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    row.forEach { label ->
                        CalculatorButton(label) {
                            when (label) {
                                "C" -> {
                                    input = "0"
                                    operator = null
                                    firstNumber = null
                                }
                                in listOf("+", "-", "*", "/") -> {
                                    operator = label
                                    firstNumber = input.toDoubleOrNull()
                                    input += label
                                }
                                "=" -> {
                                    val parts = input.split("+", "-", "*", "/")
                                    if (parts.size == 2) {
                                        val secondNumber = parts[1].toDoubleOrNull()
                                        val result = when (operator) {
                                            "+" -> logic.add(firstNumber ?: 0.0, secondNumber ?: 0.0)
                                            "-" -> logic.subtract(firstNumber ?: 0.0, secondNumber ?: 0.0)
                                            "*" -> logic.multiply(firstNumber ?: 0.0, secondNumber ?: 0.0)
                                            "/" -> logic.divide(firstNumber ?: 0.0, secondNumber ?: 0.0)
                                            else -> Double.NaN
                                        }
                                        input = if (result.isNaN()) "Ошибка" else result.toString()
                                        operator = null
                                        firstNumber = null
                                    }
                                }
                                else -> {
                                    if (input == "0") input = label else input += label
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


