package com.grappim.bankpick.compose.uikit.widgets.icon

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import com.grappim.bankpick.compose.uikit.widgets.BankPickIconButton

@Composable
fun BankPickIconWithBottomText(
    modifier: Modifier = Modifier,
    painter: Painter,
    text: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BankPickIconButton(
            onClick = onClick,
            painter = painter
        )
        Text(
            text = text,
            fontSize = 13.sp
        )
    }
}

@Composable
fun BankPickIconWithBottomText(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    text: String,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BankPickIconButton(
            onClick = onClick,
            imageVector = imageVector
        )
        Text(
            text = text,
            fontSize = 13.sp
        )
    }
}