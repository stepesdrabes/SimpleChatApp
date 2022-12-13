package cz.stepesove.simplechatapp.presentation.shared.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import cz.stepesove.simplechatapp.R

val Nunito = FontFamily(
    Font(R.font.nunito_regular, FontWeight.Normal),
    Font(R.font.nunito_semi_bold, FontWeight.SemiBold),
    Font(R.font.nunito_bold, FontWeight.Bold),
    Font(R.font.nunito_extra_bold, FontWeight.ExtraBold),
    Font(R.font.nunito_black, FontWeight.Black),
)

val Typography = Typography(
    defaultFontFamily = Nunito
)

data class TextSize(
    val small: TextUnit = 12.sp,
    val normal: TextUnit = 16.sp,
    val medium: TextUnit = 20.sp,
    val large: TextUnit = 24.sp,
    val title: TextUnit = 36.sp,
)

val LocalTextSize = compositionLocalOf { TextSize() }

val MaterialTheme.textSize: TextSize
    @Composable
    @ReadOnlyComposable
    get() = LocalTextSize.current