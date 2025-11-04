package com.example.composecalculatordiana

class CalculatorLogic {
    fun add(a: Double, b: Double) = a + b
    fun subtract(a: Double, b: Double) = a - b
    fun multiply(a: Double, b: Double) = a * b
    fun divide(a: Double, b: Double): Double {
        if (b == 0.0) throw ArithmeticException("Division by zero")
        return a / b
    }
}
