package com.gerwalex.library

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp

@Composable
fun StaggeredList(items: List<String>) {
    var animate by remember { mutableStateOf(true) }
    LaunchedEffect(Unit) {
        animate = true
    }
    LazyColumn {
        itemsIndexed(items) { index, item ->
            val animatedVisibility by animateFloatAsState(
                targetValue = if (animate) 1f else 0f,
                animationSpec = tween(
                    durationMillis = 300,
                    delayMillis = index * 100
                )
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp)
                    .alpha(animatedVisibility)
                    .offset(y = (50 * (1f - animatedVisibility)).dp)
            ) {
                Text(
                    text = item,
                    modifier = Modifier
                        .padding(16.dp)
                        .alpha(animatedVisibility)
                        .offset(y = (50 * (1f - animatedVisibility)).dp)
                )
            }
        }
    }
}