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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.gerwalex.library.R
import com.gerwalex.library.animation.HeartBeatAnimation
import com.gerwalex.library.compose.components.ElasticSearchBarDemo
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Content(modifier: Modifier = Modifier) {
    GerwalexTheme {
        Scaffold(
            modifier = modifier.safeDrawingPadding(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(stringResource(R.string.app_name))
                    },
                    actions = {
                        ElasticSearchBarDemo()
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .consumeWindowInsets(padding)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                StaggeredListDemo()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Content()
}