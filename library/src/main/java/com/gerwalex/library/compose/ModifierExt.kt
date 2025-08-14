package com.gerwalex.library.compose

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer

/**
 * Conditionally applies a modifier based on a boolean condition.
 *
 * @param condition The boolean condition to evaluate.
 * @param ifTrue A lambda function that returns the modifier to apply if the condition is true.
 * @param ifFalse A lambda function that returns the modifier to apply if the condition is false.
 *                Defaults to returning the original modifier (no change).
 * @return The modified modifier based on the condition.
 */
inline fun Modifier.conditional(
    condition: Boolean,
    ifTrue: Modifier.() -> Modifier,
    ifFalse: Modifier.() -> Modifier = { this },
): Modifier = if (condition) {
    then(ifTrue(Modifier))
} else {
    then(ifFalse(Modifier))
}

/**
 * Applies a modifier conditionally based on a boolean condition.
 *
 * This is a convenience function that simplifies applying a modifier only when a certain condition is true.
 * If the condition is false, the original modifier is returned unchanged.
 *
 * @param condition The boolean condition to evaluate.
 * @param modifier A lambda function that returns the modifier to apply if the condition is true.
 * @return The modified modifier if the condition is true, otherwise the original modifier.
 */
fun Modifier.thenIf(condition: Boolean, modifier: Modifier.() -> Modifier) =
    if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }


/**
 * Applies a scaling animation to a Composable when it is pressed.
 *
 * This modifier uses [animateFloatAsState] to smoothly transition the scale of the Composable
 * when it is pressed and released. When pressed, the Composable will scale down to 0.95f,
 * and when released, it will scale back to 1f.
 *
 * @param interactionSource The [InteractionSource] that will be used to track press interactions.
 *                          This is typically obtained from `remember { MutableInteractionSource() }`.
 * @return A [Modifier] that applies the scaling animation on press.
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

/**
 * Creates a clickable modifier that doesn't show a ripple effect.
 *
 * This modifier is useful for situations where you want to detect clicks without the visual
 * feedback of a ripple.
 *
 * @param interactionSource [MutableInteractionSource] that will be used to track interactions with this
 * clickable modifier.
 * @param onClick The callback to be invoked when this clickable modifier is clicked.
 *
 * @return A [Modifier] that decorates the current modifier to make it clickable without ripple effect.
 */
fun Modifier.clickableWithoutRipple(
    interactionSource: MutableInteractionSource,
    onClick: () -> Unit
) = this.then(
    Modifier.clickable(
        interactionSource = interactionSource,
        indication = null,
        onClick = { onClick() }
    )
)
