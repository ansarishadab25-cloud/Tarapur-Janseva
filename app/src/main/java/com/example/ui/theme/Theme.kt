package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

private val JansevaLightColorScheme =
  lightColorScheme(
    primary = DeepNavyBlue,
    onPrimary = PureWhite,
    primaryContainer = SoftBlueContainer,
    onPrimaryContainer = OnSoftBlueContainer,
    secondary = VillageGreen,
    onSecondary = PureWhite,
    secondaryContainer = SoftGreenContainer,
    onSecondaryContainer = OnSoftGreenContainer,
    tertiary = RoyalBlueAccent,
    onTertiary = PureWhite,
    background = PureWhite,
    onBackground = TextPrimary,
    surface = PureWhite,
    onSurface = TextPrimary,
    surfaceVariant = SoftSurface,
    onSurfaceVariant = TextSecondary,
    outline = SubtleBorder,
    error = ErrorRed,
    onError = PureWhite,
    errorContainer = ErrorContainer,
    onErrorContainer = ErrorRed,
  )

val JansevaShapes =
  Shapes(
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(22.dp),
    extraLarge = RoundedCornerShape(28.dp),
  )

@Composable
fun TarapurJansevaTheme(content: @Composable () -> Unit) {
  MaterialTheme(
    colorScheme = JansevaLightColorScheme,
    typography = JansevaTypography,
    shapes = JansevaShapes,
    content = content,
  )
}
