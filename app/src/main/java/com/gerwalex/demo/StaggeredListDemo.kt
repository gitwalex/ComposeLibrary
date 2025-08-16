package com.gerwalex.demo

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.gerwalex.library.MorphingFABDemo
import com.gerwalex.library.compose.components.BreathingButtonDemo
import com.gerwalex.library.modifier.JumpOnClickDemo

sealed class ScreenItem(val content: @Composable () -> Unit)
object BreathingButton : ScreenItem({ BreathingButtonDemo() })
object JumpOnClickDemo : ScreenItem({ JumpOnClickDemo() })
object MorphingFABDemo : ScreenItem({ MorphingFABDemo() })

val list = listOf(
    BreathingButton,
    JumpOnClickDemo,
    MorphingFABDemo,
)

@Composable
fun StaggeredListDemo() {
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
                item.content()
            }
        }
    }
}