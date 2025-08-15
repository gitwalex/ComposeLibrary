package com.gerwalex.library.compose

import androidx.compose.ui.Modifier

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
        then(modifier(Modifier.Companion))
    } else {
        this
    }