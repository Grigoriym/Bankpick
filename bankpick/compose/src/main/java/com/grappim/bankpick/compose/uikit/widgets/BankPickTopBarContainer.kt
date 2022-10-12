package com.grappim.bankpick.compose.uikit.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.grappim.bankpick.compose.ui.theme.DefaultHorizontalPadding

@Composable
fun BankPickTopBarContainer(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier
            .systemBarsPadding()
            .fillMaxWidth()
            .padding(
                top = 8.dp,
                bottom = 4.dp
            )
            .padding(
                horizontal = DefaultHorizontalPadding
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = horizontalArrangement
    ) {
        content()
    }
}