package com.gerwalex.batteryguard.ui.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class ExtendedColorScheme(
    val material: ColorScheme,
    val chargeColor: Color = Color.Unspecified,
    val midChargeColor: Color = Color.Unspecified,
    val goodChargeColor: Color = Color.Unspecified,
    val lowChargeColor: Color = Color.Unspecified,
    val arc_background_color: Color = Color.Unspecified,
    val midTempColor: Color = Color.Unspecified,
    val goodTempColor: Color = Color.Unspecified,
    val hotTempColor: Color = Color.Unspecified,
    val screenOnColor: Color = Color.Unspecified,
    val dozeModeColor: Color = Color.Unspecified,
    val pluggedColor: Color = Color.Unspecified,
    val screenOffColor: Color = Color.Unspecified,
    val midHealthColor: Color = Color.Unspecified,
    val goodHealthColor: Color = Color.Unspecified,
    val badHealthColor: Color = Color.Unspecified,
)

val chargeColorLight = Color(0xFF0099CC)
val midChargeColorLight = Color(0xFFFFF257)
val goodChargeColorLight = Color(0xFF99CC00)
val lowChargeColorLight = Color(0xFFFF4444)
val arc_background_colorLight = Color(0xFFEEEEEE)

val midTempColorLight = Color(0xFFFFF257)
val goodTempColorLight = Color(0xFF99CC00)
val hotTempColorLight = Color(0xFFFF4444)

val midHealthColorLight = Color(0xFFFFF257)
val goodHealthColorLight = Color(0xFF99CC00)
val badHealthColorLight = Color(0xFFFF4444)

val screenOnColorLight: Color = Color(0xFFFFF257)
val dozeModeColorLight: Color = Color(0xFF99CC00)
val pluggedColorLight: Color = Color(0xFFFF4444)
val screenOffColorLight: Color = Color(0xFFEEEEEE)

val chargeColorDark = Color(0xFF0099CC)
val midChargeColorDark = Color(0xFFFFF257)
val goodChargeColorDark = Color(0xFF99CC00)
val lowChargeColorDark = Color(0xFFFF4444)
val arc_background_colorDark = Color(0xFFEEEEEE)

val midTempColorDark = Color(0xFFFFF257)
val goodTempColorDark = Color(0xFF99CC00)
val hotTempColorDark = Color(0xFFFF4444)

val screenOnColorDark: Color = Color(0xFFFFF257)
val dozeModeColorDark: Color = Color(0xFF99CC00)
val pluggedColorDark: Color = Color(0xFFFF4444)
val screenOffColorDark: Color = Color(0xFFEEEEEE)

val midHealthColorDark = Color(0xFFFFF257)
val goodHealthColorDark = Color(0xFF99CC00)
val badHealthColorDark = Color(0xFFFF4444)

val extendedLight = ExtendedColorScheme(
    material = lightScheme,
    chargeColor = chargeColorLight,
    midChargeColor = midChargeColorLight,
    goodChargeColor = goodChargeColorLight,
    lowChargeColor = lowChargeColorLight,
    arc_background_color = arc_background_colorLight,
    midTempColor = midTempColorLight,
    goodTempColor = goodTempColorLight,
    hotTempColor = hotTempColorLight,
    screenOnColor = screenOnColorLight,
    dozeModeColor = dozeModeColorLight,
    pluggedColor = pluggedColorLight,
    screenOffColor = screenOffColorLight,
    midHealthColor = midHealthColorLight,
    goodHealthColor = goodHealthColorLight,
    badHealthColor = badHealthColorLight,
)

val extendedDark = ExtendedColorScheme(
    material = darkScheme,
    chargeColor = chargeColorDark,
    midChargeColor = midChargeColorDark,
    goodChargeColor = goodChargeColorDark,
    lowChargeColor = lowChargeColorDark,
    arc_background_color = arc_background_colorDark,
    midTempColor = midTempColorDark,
    goodTempColor = goodTempColorDark,
    hotTempColor = hotTempColorDark,
    screenOnColor = screenOnColorDark,
    dozeModeColor = dozeModeColorDark,
    pluggedColor = pluggedColorDark,
    screenOffColor = screenOffColorDark,
    midHealthColor = midHealthColorDark,
    goodHealthColor = goodHealthColorDark,
    badHealthColor = badHealthColorDark,
)
