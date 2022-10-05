package com.grappim.bankpick.uikit.widgets

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.grappim.bankpick.ui.theme.BankPickTheme

@Composable
fun BankPickBackButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    BankPickIconButton(
        onClick = onClick,
        imageVector = Icons.Filled.ChevronLeft,
        modifier = modifier
    )
}

@Composable
@Preview(
    showBackground = true
)
private fun BankPickBackButtonPreview() {
    BankPickTheme {
        BankPickBackButton(onClick = {})
    }
}

@Composable
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
private fun BankPickBackButtonPreviewNight() {
    BankPickTheme {
        BankPickBackButton(onClick = {})
    }
}
