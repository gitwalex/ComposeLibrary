package com.gerwalex.library.compose

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer

/**
 * Applies a scaling animation to a Composable when it is pressed.
 *
 * This modifier uses [androidx.compose.animation.core.animateFloatAsState] to smoothly transition the scale of the Composable
 * when it is pressed and released. When pressed, the Composable will scale down to 0.95f,
 * and when released, it will scale back to 1f.
 *
 * @param interactionSource The [androidx.compose.foundation.interaction.InteractionSource] that will be used to track press interactions.
 *                          This is typically obtained from `remember { MutableInteractionSource() }`.
 * @return A [androidx.compose.ui.Modifier] that applies the scaling animation on press.
 *
 * @see <a href="https://medium.com/@alohaabhi/beautiful-way-to-access-touch-interactions-in-jetpack-compose-c4b8444b5c95">Beautiful way to access touch interactions in Jetpack Compose</a>
 */
fun Modifier.scaleOnPress(
    interactionSource: InteractionSource
) = composed {
    val isPressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        if (isPressed) {
            0.95f
        } else {
            1f
        }, label = "ScaleOnPress"
    )
    graphicsLayer {
        scaleX = scale
        scaleY = scale
    }
}