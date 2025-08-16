package com.gerwalex.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.gerwalex.library.R
import com.gerwalex.library.animation.HeartBeatAnimation
import com.gerwalex.library.compose.components.BreathingButtonDemo
import com.gerwalex.library.modifier.JumpOnClickDemo
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

sealed class ScreenItem
object BreathingButton : ScreenItem()
object JumpOnClickDemo : ScreenItem()

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
                StaggeredListDemo(
                    listOf(
                        BreathingButton,
                        JumpOnClickDemo
                    )
                )
            }
        }
    }
}

@Composable
fun StaggeredListDemo(list: List<ScreenItem>) {
    var animate by remember { mutableStateOf(true) }
    LaunchedEffect(Unit) {
        animate = true
    }
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        itemsIndexed(list) { index, item ->
            val animatedVisibility by animateFloatAsState(
                targetValue = if (animate) 1f else 0f,
                animationSpec = tween(
                    durationMillis = 300,
                    delayMillis = index * 100
                )
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(animatedVisibility)
                    .offset(y = (50 * (1f - animatedVisibility)).dp),
                contentAlignment = Alignment.Center
            ) {
                when (item) {
                    is BreathingButton -> BreathingButtonDemo()
                    is JumpOnClickDemo -> JumpOnClickDemo()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Content()
}