package com.gerwalex.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.gerwalex.library.R
import com.gerwalex.library.StaggeredList
import com.gerwalex.library.animation.HeartBeatAnimation
import com.gerwalex.library.compose.calculator.CalculatorScreen
import com.gerwalex.library.compose.components.BreathingButton
import com.gerwalex.library.theme.GerwalexTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.kibotu.splashscreen.SplashScreenDecorator
import net.kibotu.splashscreen.splash
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

class MainActivity : ComponentActivity() {
    private var splashScreen: SplashScreenDecorator? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        showSplash()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Content()
        }
        lifecycleScope.launch {
            // delay os splash screen
            delay(1.seconds)
            splashScreen?.shouldKeepOnScreen = false
            // delay custom splash screen
            delay(3.seconds)
            splashScreen?.dismiss()
        }

    }

    private fun showSplash() {

        val exitDuration = 800L
        val fadeDurationOffset = 200L

        splashScreen = splash {
            content {
                exitAnimationDuration = exitDuration
                composeViewFadeDurationOffset = fadeDurationOffset
                HeartBeatAnimation(
                    isVisible = isVisible.value,
                    exitAnimationDuration = exitAnimationDuration.milliseconds,
                    onStartExitAnimation = { startExitAnimation() },
                    content = {
                        Image(
                            painter = painterResource(R.drawable.app_logo),
                            contentDescription = null
                        )
                    }
                )
            }
        }

    }

    override fun onDestroy() {
        splashScreen = null
        super.onDestroy()
    }
}


@Composable
fun Content(modifier: Modifier = Modifier) {
    GerwalexTheme {
        Scaffold(modifier = modifier.safeDrawingPadding()) { padding ->
            Column(
                modifier = Modifier
                    .consumeWindowInsets(padding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
//                BreathingButtonDemo()
//                StaggeredListDemo()
                CalculatorScreen()
            }
        }
    }
}

@Composable
fun StaggeredListDemo(modifier: Modifier = Modifier) {
    val list = listOf(
        "Item",
        "Item",
        "Item",
        "Item",
        "Item",
        "Item",
        "Item",
        "Item",
        "Item",
        "Item",
        "Item",
        "Item",
        "Item",
        "Item",
        "Item",
        "Item",
        "Item",
        "Item",
        "Item",
        "Item",
        "Item",
        "Item",
        "Item",
        "Item",
        "Item",
        "Item",
        "Item",
    )
    StaggeredList(list)
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