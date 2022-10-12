package com.grappim.bankpick.compose.uikit.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.grappim.bankpick.compose.ui.theme.BankPickTheme

@Composable
fun BankPickProgressDialog(
    show: Boolean,
    onClose: (() -> Unit)? = null
) {
    if (show) {
        Dialog(
            onDismissRequest = {
                onClose?.invoke()
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .background(
                        color = White,
                        shape = RoundedCornerShape(12.dp)
                    )
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
@Preview
private fun BankPickProgressDialogPreview() {
    BankPickTheme {
        BankPickProgressDialog(true)
    }
}