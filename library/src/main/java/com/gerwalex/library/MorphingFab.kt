package com.gerwalex.library

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun MorphingFABDemo(modifier: Modifier = Modifier) {
    MorphingFAB(
        modifier = modifier,
        icon = {
            Icon(
                Icons.Default.Add,
                contentDescription = "Add",
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }, expandedContent = {
            LazyColumn(
                modifier = Modifier.padding(16.dp)
            ) {
                items(3) { index ->
                    Text(text = "Action ${index + 1}")
                }
            }

        })
}

@Composable
fun MorphingFAB(
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    expandedContent: @Composable () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val transition = updateTransition(targetState = expanded, label = "fab_transition")

    val fabSize by transition.animateDp(
        transitionSpec = { spring(dampingRatio = Spring.DampingRatioMediumBouncy) },
        label = "size",
    ) { state ->
        if (state) 300.dp else 56.dp
    }

    val cornerRadius by transition.animateDp(
        transitionSpec = { spring(dampingRatio = Spring.DampingRatioMediumBouncy) },
        label = "radius"
    ) { state ->
        if (state) 16.dp else 28.dp
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(fabSize)
            .clip(RoundedCornerShape(cornerRadius))
            .background(MaterialTheme.colorScheme.primary)
            .clickable { expanded = !expanded }
    ) {
        if (expanded)
            expandedContent() else icon()
    }
}
