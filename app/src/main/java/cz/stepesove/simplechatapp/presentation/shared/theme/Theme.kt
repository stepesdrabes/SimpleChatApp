package cz.stepesove.simplechatapp.presentation.shared.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = HighlightBlue,
    secondary = HighlightBlue,
    background = DarkBackground,
    onPrimary = White,
    onSecondary = White,
    onBackground = White,
    surface = DarkBoxBg,
    onSurface = DarkTextOnBox,
)

private val LightColorPalette = lightColors(
    primary = HighlightBlue,
    secondary = HighlightBlue,
    background = LightBackground,
    onPrimary = White,
    onSecondary = White,
    onBackground = OffBlack,
    surface = LightBoxBg,
    onSurface = LightTextOnBox,
)

@Composable
fun SimpleChatAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalSpacing provides Spacing(),
        LocalTextSize provides TextSize(),
    ) {
        MaterialTheme(
            colors = if (darkTheme) DarkColorPalette else LightColorPalette,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }
}