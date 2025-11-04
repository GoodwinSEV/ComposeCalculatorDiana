package com.example.composecalculatordiana

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.composecalculatordiana.ui.theme.ComposeCalculatorDianaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeCalculatorDianaTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color(0xFFF5F5DC))
                ) {
                    MainScreen()
                }
            }
        }
    }
}

