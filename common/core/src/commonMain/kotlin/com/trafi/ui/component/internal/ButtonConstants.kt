package com.trafi.ui.component.internal

import com.trafi.ui.theme.internal.BasicFontWeight.*
import com.trafi.ui.theme.internal.CurrentTheme
import com.trafi.ui.theme.internal.copy
import com.trafi.ui.theme.internal.dp
import com.trafi.ui.theme.internal.tint

class ButtonConstants(theme: CurrentTheme) {
    val defaultColor = theme.colorPalette.primary
    val defaultContentColor = theme.colorPalette.onPrimary
    val disabledColor = theme.colorPalette.primary.tint(0.38f)
    val textStyle = theme.typographyScale.textL.copy(fontWeight = Bold)
    val minHeight = 48.dp
    val cornerRadius = theme.cornerRadiusScale.buttonRadius
    val defaultTruncate = true
}
