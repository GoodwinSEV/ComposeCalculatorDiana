package com.example.composecalculatordiana

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable

@Composable
fun MainScreen() {
    var display by remember { mutableStateOf("0") }
    var firstNumber by remember { mutableStateOf<Double?>(null) }
    var operator by remember { mutableStateOf<String?>(null) }

    val logic = remember { CalculatorLogic() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 60.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CalculatorDisplay(value = display)

        val buttons = listOf(
            listOf("7", "8", "9", "/"),
            listOf("4", "5", "6", "*"),
            listOf("1", "2", "3", "-"),
            listOf("0", "C", "=", "+")
        )

        buttons.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                row.forEach { symbol ->
                    CalculatorButton(
                        symbol = symbol,
                        modifier = Modifier.weight(1f).height(80.dp)
                    ) {
                        when (symbol) {
                            "C" -> {
                                display = "0"
                                firstNumber = null
                                operator = null
                            }
                            "+", "-", "*", "/" -> {
                                firstNumber = display.toDoubleOrNull()
                                operator = symbol
                                display = "0"
                            }
                            "=" -> {
                                val secondNumber = display.toDoubleOrNull()
                                if (firstNumber != null && secondNumber != null && operator != null) {
                                    display = try {
                                        when (operator) {
                                            "+" -> logic.add(firstNumber!!, secondNumber).toString()
                                            "-" -> logic.subtract(firstNumber!!, secondNumber).toString()
                                            "*" -> logic.multiply(firstNumber!!, secondNumber).toString()
                                            "/" -> logic.divide(firstNumber!!, secondNumber).toString()
                                            else -> "0"
                                        }
                                    } catch (_: Exception) {
                                        "Error"
                                    }
                                }
                                firstNumber = null
                                operator = null
                            }
                            else -> {
                                display = if (display == "0") symbol else display + symbol
                            }
                        }
                    }
                }
            }
        }
    }
}

