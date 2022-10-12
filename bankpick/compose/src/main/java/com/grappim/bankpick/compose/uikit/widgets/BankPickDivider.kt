package com.grappim.bankpick.compose.uikit.widgets

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.grappim.bankpick.compose.ui.theme.BankPickBlackRussian2
import com.grappim.bankpick.compose.ui.theme.BankPickWhiteSmoke1

@Composable
fun BankPickDivider(
    modifier: Modifier = Modifier
) {
    val color = if (isSystemInDarkTheme()) {
        BankPickBlackRussian2
    } else {
        BankPickWhiteSmoke1
    }
    Divider(
        modifier = modifier,
        color = color
    )
}