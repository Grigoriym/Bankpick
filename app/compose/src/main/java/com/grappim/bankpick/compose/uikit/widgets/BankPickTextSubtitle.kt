package com.grappim.bankpick.compose.uikit.widgets

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.grappim.bankpick.compose.ui.theme.BankPickSpunPearl

@Composable
fun BankPickTextSubtitle(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = BankPickSpunPearl,
    isError: Boolean = false
) {
    val textColor = if (isError) {
        MaterialTheme.colors.error
    } else {
        color
    }
    Text(
        text = text,
        modifier = modifier,
        fontSize = 14.sp,
        color = textColor
    )
}