package com.example.composecalculatordiana

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecalculatordiana.ui.theme.ComposeCalculatorDianaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeCalculatorDianaTheme {
                CalculatorUI()
            }
        }
    }
}

@Composable
fun CalculatorUI() {
    var input by remember { mutableStateOf("0") }

    val beigeBackground = Color(0xFFF7F3E9)
    val pastelGreen = Color(0xFFB7E1A1)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(beigeBackground)
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(8.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Text(
                text = input,
                fontSize = 48.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End,
                maxLines = 2
            )
        }

        val buttons = listOf(
            listOf("7", "8", "9", "/"),
            listOf("4", "5", "6", "*"),
            listOf("1", "2", "3", "-"),
            listOf("0", ".", "C", "+"),
            listOf("=")
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            buttons.forEach { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    row.forEach { label ->
                        Button(
                            onClick = {
                                when (label) {
                                    "C" -> input = "0"
                                    "=" -> {  }
                                    else -> {
                                        input = if (input == "0") label else input + label
                                    }
                                }
                            },
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = pastelGreen,
                                contentColor = Color.Black
                            ),
                            modifier = Modifier
                                .weight(1f)
                                .height(72.dp)
                        ) {
                            Text(text = label, fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }
        }
    }
}

