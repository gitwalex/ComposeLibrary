package com.gerwalex.compose


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.TextStyle

/**
 * A Composable function that displays text with a typewriter animation effect.
 *
 * This function animates the display of the input `text` character by character,
 * simulating a typing effect.
 *
 * @param text The string to be displayed with the typewriter animation.
 * @param modifier Optional [Modifier] to be applied to the layout.
 * @param isVisible Controls the visibility and animation state. If `true`, the animation plays.
 *                  If `false`, the animation snaps to the beginning.
 * @param spec The [AnimationSpec] to control the animation timing and curve.
 *             Defaults to a [tween] animation with a duration proportional to the text length
 *             (50ms per character) and a [LinearEasing].
 * @param style The [TextStyle] to apply to the displayed text. Defaults to [LocalTextStyle.current].
 * @param preoccupySpace If `true`, the composable will occupy the full space the `text` will take
 *                       even while animating. This is achieved by rendering the full `text` invisibly
 *                       in the background. Defaults to `true`.
 * @param onComplete A lambda function that will be invoked when the typewriter animation finishes.
 *
 * @see <a href="https://medium.com/@kappdev/crafting-typewrite-text-animation-custom-quote-card-with-jetpack-compose-92ab76582efb">Typewrite Text Animation</a>
 */
@Composable
fun TypewriteText(
    text: String,
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
    spec: AnimationSpec<Int> = tween(durationMillis = text.length * 50, easing = LinearEasing),
    style: TextStyle = LocalTextStyle.current,
    preoccupySpace: Boolean = true,
    onComplete: () -> Unit,
) {
    // State that keeps the text that is currently animated
    var textToAnimate by remember { mutableStateOf("") }

    // Animatable index to control the progress of the animation
    val index = remember {
        Animatable(initialValue = 0, typeConverter = Int.VectorConverter)
    }

    LaunchedEffect(index.isRunning) {
        if (index.value > 0 && !index.isRunning) {
            onComplete()

        }
    }

    // Effect to handle animation when visibility changes
    LaunchedEffect(isVisible) {
        if (isVisible) {
            // Start animation if visible
            textToAnimate = text
            index.animateTo(text.length, spec)
        } else {
            // Snap to the beginning if not visible
            index.snapTo(0)
        }
    }

    // Effect to handle animation when text content changes
    LaunchedEffect(text) {
        if (isVisible) {
            // Reset animation and update text if visible
            index.snapTo(0)
            textToAnimate = text
            index.animateTo(text.length, spec)
        }
    }

    // Box composable to contain the animated and static text
    Box(modifier = modifier) {
        if (preoccupySpace && index.isRunning) {
            // Display invisible text when preoccupation is turned on
            // and the animation is in progress.
            // Plays the role of a placeholder to occupy the space
            // that will be filled with text.
            Text(
                text = text,
                style = style,
                modifier = Modifier.alpha(0f)
            )
        }

        // Display animated text based on the current index value
        Text(
            text = textToAnimate.substring(0, index.value),
            style = style
        )
    }
}