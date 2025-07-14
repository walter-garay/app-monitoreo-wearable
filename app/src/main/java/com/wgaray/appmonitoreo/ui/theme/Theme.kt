package com.wgaray.appmonitoreo.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

val FondoApp = Color(0xFFF6F2FF) // Morado claro para fondo de app

private val DarkColorScheme = darkColorScheme(
    primary = MoradoPrincipal,
    primaryContainer = MoradoOscuro, // más oscuro para contraste en dark
    onPrimary = Color.White,
    background = FondoApp,
    onBackground = Color(0xFFEDEDED), // texto claro para contraste
    surface = Color(0xFF1E1E1E), // superficies un poco más claras que el fondo
    onSurface = Color(0xFFEDEDED), // texto sobre superficie
    secondary = MoradoClaro, // un morado suave para acentos
    onSecondary = Color.Black
)

private val LightColorScheme = lightColorScheme(
    primary = MoradoPrincipal,
    primaryContainer = MoradoClaro,
    onPrimary = Color.White,
    background = FondoApp,
    onBackground = TextoPrimario,
    surface = Color.White,
    onSurface = TextoPrimario,
    secondary = MoradoOscuro,
    onSecondary = Color.White

    /* Other default colors to override
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
    fun AppMonitoreoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false, // Por defecto vino en true
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}