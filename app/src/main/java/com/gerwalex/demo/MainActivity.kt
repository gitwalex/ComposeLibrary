package com.gerwalex.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gerwalex.demo.ui.theme.ComposeTheme
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Content()
        }
    }
}

@Composable
fun Content(modifier: Modifier = Modifier) {
    ComposeTheme {
        Scaffold(modifier = modifier.safeDrawingPadding()) { padding ->
            Column(modifier = Modifier.consumeWindowInsets(padding)) {
                BreathingButtonDemo()
            }
        }
    }
}

@Composable
fun BreathingButtonDemo(modifier: Modifier = Modifier) {
    var isLoading by remember { mutableStateOf(false) }
    LaunchedEffect(isLoading) {
        if (isLoading) {
            delay(5.seconds)
            isLoading = false
        }
    }
    BreathingButton(isLoading = isLoading, modifier = modifier, onClick = { isLoading = true })
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Content()
}