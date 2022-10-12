package com.grappim.bankpick.compose.uikit.widgets

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarData
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.grappim.bankpick.compose.ui.theme.BankPickBlackRussian

@Composable
fun BankPickSnackbar(
    snackbarData: SnackbarData
) {
    Snackbar(
        snackbarData = snackbarData,
        contentColor = BankPickBlackRussian,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(8.dp)
    )
}