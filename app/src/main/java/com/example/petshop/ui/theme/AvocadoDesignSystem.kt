package com.example.avocadopetshop.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Avocado PetShop Design System 1.0
 * Color values are taken from the design-system specification supplied by the team.
 * The Figma file remains the source of truth for exact layout and dimensions.
 */
object AvocadoColors {
    val GlobalBackground = Color(0xFFFFF8F5)
    val CardSurface = Color(0xFFFFFFFF)
    val SecondarySurface = Color(0xFFFFF1EA)
    val Border = Color(0xFFF6D3C0)

    val Primary = Color(0xFFFF7B75)
    val PrimarySoft = Color(0xFFFFE8E2)
    val Maroon = Color(0xFF8C332E)

    val IconBackground = Color(0xFFFFE2DC)
    val SuccessBackground = Color(0xFFDCFCE7)
    val Success = Color(0xFF15803D)
    val WarningBackground = Color(0xFFFEF3C7)
    val Warning = Color(0xFFB45309)
    val DangerBackground = Color(0xFFFEE2E2)
    val Danger = Color(0xFFDC2626)

    val PrimaryText = Color(0xFF29170C)
    val SecondaryText = Color(0xFF4E2F1D)
    val MutedText = Color(0xFF76533F)
    val Placeholder = Color(0xFFA88E81)
    val OnPrimary = Color(0xFFFFFFFF)
}

/**
 * Fallback font family. To use the exact Plus Jakarta Sans font, add the supplied
 * font files to res/font and replace this with FontFamily(Font(R.font...)).
 */
val AvocadoFontFamily = FontFamily.SansSerif

private val AvocadoTypography = androidx.compose.material3.Typography(
    displayLarge = TextStyle(fontFamily = AvocadoFontFamily, fontWeight = FontWeight.ExtraBold, fontSize = 30.sp, lineHeight = 38.sp),
    headlineLarge = TextStyle(fontFamily = AvocadoFontFamily, fontWeight = FontWeight.Bold, fontSize = 22.sp, lineHeight = 28.sp),
    headlineMedium = TextStyle(fontFamily = AvocadoFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 18.sp, lineHeight = 24.sp),
    bodyLarge = TextStyle(fontFamily = AvocadoFontFamily, fontWeight = FontWeight.Normal, fontSize = 16.sp, lineHeight = 24.sp),
    bodyMedium = TextStyle(fontFamily = AvocadoFontFamily, fontWeight = FontWeight.Normal, fontSize = 14.sp, lineHeight = 20.sp),
    bodySmall = TextStyle(fontFamily = AvocadoFontFamily, fontWeight = FontWeight.Normal, fontSize = 12.sp, lineHeight = 16.sp),
    labelSmall = TextStyle(fontFamily = AvocadoFontFamily, fontWeight = FontWeight.Medium, fontSize = 10.sp, lineHeight = 14.sp)
)

@Composable
fun AvocadoTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = AvocadoColors.Primary,
            onPrimary = AvocadoColors.OnPrimary,
            secondary = AvocadoColors.Maroon,
            background = AvocadoColors.GlobalBackground,
            surface = AvocadoColors.CardSurface,
            onSurface = AvocadoColors.PrimaryText,
            outline = AvocadoColors.Border,
            error = AvocadoColors.Danger
        ),
        typography = AvocadoTypography,
        content = content
    )
}
