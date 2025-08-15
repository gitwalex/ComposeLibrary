package com.gerwalex.library.compose

import androidx.compose.ui.Modifier

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
    then(ifTrue(Modifier.Companion))
} else {
    then(ifFalse(Modifier.Companion))
}