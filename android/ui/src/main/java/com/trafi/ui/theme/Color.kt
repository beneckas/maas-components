@file:Suppress("unused")

package com.trafi.ui.theme

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import com.trafi.ui.theme.internal.ColorPalette

@Stable val Grey100 = Color(ColorPalette.Grey100)
@Stable val Grey200 = Color(ColorPalette.Grey200)
@Stable val Grey300 = Color(ColorPalette.Grey300)
@Stable val Grey400 = Color(ColorPalette.Grey400)
@Stable val Grey500 = Color(ColorPalette.Grey500)
@Stable val Grey600 = Color(ColorPalette.Grey600)
@Stable val Grey700 = Color(ColorPalette.Grey700)
@Stable val Grey800 = Color(ColorPalette.Grey800)
@Stable val Grey900 = Color(ColorPalette.Grey900)

fun MaasTheme.lightColors(
    primary: Color = Color(ColorPalette.DefaultLight.Primary),
    primaryVariant: Color = Color(ColorPalette.DefaultLight.PrimaryVariant),
    secondary: Color = Color(ColorPalette.DefaultLight.Secondary),
    secondaryVariant: Color = Color(ColorPalette.DefaultLight.SecondaryVariant),
    background: Color = Color(ColorPalette.DefaultLight.Background),
    surface: Color = Color(ColorPalette.DefaultLight.Surface),
    error: Color = Color(ColorPalette.DefaultLight.Error),
    onPrimary: Color = Color(ColorPalette.DefaultLight.OnPrimary),
    onSecondary: Color = Color(ColorPalette.DefaultLight.OnSecondary),
    onBackground: Color = Color(ColorPalette.DefaultLight.OnBackground),
    onSurface: Color = Color(ColorPalette.DefaultLight.OnSurface),
    onError: Color = Color(ColorPalette.DefaultLight.OnError)
): MaasColorPalette = MaasColorPalette(
    primary = primary,
    primaryVariant = primaryVariant,
    secondary = secondary,
    secondaryVariant = secondaryVariant,
    background = background,
    surface = surface,
    error = error,
    onPrimary = onPrimary,
    onSecondary = onSecondary,
    onBackground = onBackground,
    onSurface = onSurface,
    onError = onError,
    isLight = true
)

fun MaasTheme.darkColors(
    primary: Color = Color(ColorPalette.DefaultDark.Primary),
    primaryVariant: Color = Color(ColorPalette.DefaultDark.PrimaryVariant),
    secondary: Color = Color(ColorPalette.DefaultDark.Secondary),
    secondaryVariant: Color = Color(ColorPalette.DefaultDark.SecondaryVariant),
    background: Color = Color(ColorPalette.DefaultDark.Background),
    surface: Color = Color(ColorPalette.DefaultDark.Surface),
    error: Color = Color(ColorPalette.DefaultDark.Error),
    onPrimary: Color = Color(ColorPalette.DefaultDark.OnPrimary),
    onSecondary: Color = Color(ColorPalette.DefaultDark.OnSecondary),
    onBackground: Color = Color(ColorPalette.DefaultDark.OnBackground),
    onSurface: Color = Color(ColorPalette.DefaultDark.OnSurface),
    onError: Color = Color(ColorPalette.DefaultDark.OnError)
): MaasColorPalette = MaasColorPalette(
    primary = primary,
    primaryVariant = primaryVariant,
    secondary = secondary,
    secondaryVariant = secondaryVariant,
    background = background,
    surface = surface,
    error = error,
    onPrimary = onPrimary,
    onSecondary = onSecondary,
    onBackground = onBackground,
    onSurface = onSurface,
    onError = onError,
    isLight = false
)

@Stable
class MaasColorPalette(
    primary: Color,
    primaryVariant: Color,
    secondary: Color,
    secondaryVariant: Color,
    background: Color,
    surface: Color,
    error: Color,
    onPrimary: Color,
    onSecondary: Color,
    onBackground: Color,
    onSurface: Color,
    onError: Color,
    isLight: Boolean
) {
    var primary by mutableStateOf(primary)
        private set
    var primaryVariant by mutableStateOf(primaryVariant)
        private set
    var secondary by mutableStateOf(secondary)
        private set
    var secondaryVariant by mutableStateOf(secondaryVariant)
        private set
    var background by mutableStateOf(background)
        private set
    var surface by mutableStateOf(surface)
        private set
    var error by mutableStateOf(error)
        private set
    var onPrimary by mutableStateOf(onPrimary)
        private set
    var onSecondary by mutableStateOf(onSecondary)
        private set
    var onBackground by mutableStateOf(onBackground)
        private set
    var onSurface by mutableStateOf(onSurface)
        private set
    var onError by mutableStateOf(onError)
        private set
    var isLight by mutableStateOf(isLight)
        private set

    fun updateColorsFrom(other: MaasColorPalette) {
        primary = other.primary
        primaryVariant = other.primaryVariant
        secondary = other.secondary
        secondaryVariant = other.secondaryVariant
        background = other.background
        surface = other.surface
        error = other.error
        onPrimary = other.onPrimary
        onSecondary = other.onSecondary
        onBackground = other.onBackground
        onSurface = other.onSurface
        onError = other.onError
        isLight = other.isLight
    }
}
