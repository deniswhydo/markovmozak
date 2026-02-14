package com.markovmozak.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = MarkoOrange,
    onPrimary = MarkoWhite,
    primaryContainer = MarkoOrangeLight,
    onPrimaryContainer = MarkoOrangeDark,
    secondary = MarkoBlue,
    onSecondary = MarkoWhite,
    secondaryContainer = MarkoBlueLight,
    onSecondaryContainer = MarkoBlueDark,
    background = MarkoCream,
    onBackground = MarkoGrayDark,
    surface = MarkoWhite,
    onSurface = MarkoGrayDark,
    surfaceVariant = MarkoGrayLight,
    onSurfaceVariant = MarkoGray,
    error = MarkoRed,
    onError = MarkoWhite,
    errorContainer = MarkoRedLight,
    onErrorContainer = MarkoWhite
)

@Composable
fun MarkovMozakTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
