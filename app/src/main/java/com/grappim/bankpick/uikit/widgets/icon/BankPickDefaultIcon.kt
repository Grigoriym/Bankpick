package com.grappim.bankpick.uikit.widgets.icon

import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import com.grappim.bankpick.ui.theme.BankPickSpunPearl

@Composable
fun BankPickDefaultIcon(
    modifier: Modifier = Modifier,
    painter: Painter,
    tint: Color = BankPickSpunPearl
) {
    Icon(
        modifier = modifier,
        painter = painter,
        contentDescription = "",
        tint = tint
    )
}

@Composable
fun BankPickDefaultIcon(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    tint: Color = BankPickSpunPearl
) {
    Icon(
        modifier = modifier,
        imageVector = imageVector,
        contentDescription = "",
        tint = tint
    )
}