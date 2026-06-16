package org.vz.nxo.core.designsystem

import android.app.Application
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val VibrantDarkColorScheme = darkColorScheme(
    primary = NeonViolet,
    secondary = ElectricBlue,
    tertiary = VividMagenta,
    background = DeepBlack,
    surface = SurfaceDark,
    onPrimary = DeepBlack,
    onSecondary = DeepBlack,
    onTertiary = DeepBlack,
    onBackground = SurfaceLighter,
    onSurface = SurfaceLighter
)

@Composable
fun NxoTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = VibrantDarkColorScheme,
        typography = Typography,
        content = content
    )
}
