package com.gerwalex.library.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier


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
