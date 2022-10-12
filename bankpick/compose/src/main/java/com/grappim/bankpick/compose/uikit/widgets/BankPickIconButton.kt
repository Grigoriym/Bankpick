package com.grappim.bankpick.compose.uikit.widgets

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.grappim.bankpick.compose.ui.theme.BankPickBlackRussian
import com.grappim.bankpick.compose.ui.theme.BankPickTheme
import com.grappim.bankpick.compose.ui.theme.BankPickWhiteSmoke
import com.grappim.bankpick.compose.uikit.widgets.icon.BankPickDefaultIcon

@Composable
fun BankPickIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    imageVector: ImageVector
) {
    ButtonContainer(
        onClick = onClick,
        modifier = modifier
    ) {
        BankPickDefaultIcon(
            imageVector = imageVector,
            tint = iconTint()
        )
    }
}

@Composable
fun BankPickIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    painter: Painter
) {
    ButtonContainer(
        onClick = onClick,
        modifier = modifier
    ) {
        BankPickDefaultIcon(
            painter = painter,
            tint = iconTint()
        )
    }
}

@Composable
private fun iconTint(): Color {
    val darkTheme: Boolean = isSystemInDarkTheme()
    return if (darkTheme) {
        Color.White
    } else {
        BankPickBlackRussian
    }
}

@Composable
private fun ButtonContainer(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    content: @Composable () -> Unit

) {
    val darkTheme: Boolean = isSystemInDarkTheme()
    val backgroundColor = if (darkTheme) {
        BankPickBlackRussian
    } else {
        BankPickWhiteSmoke
    }
    IconButton(
        onClick = onClick,
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = CircleShape
            )
    ) {
        content()
    }
}

@Composable
@Preview
private fun BankPickIconButtonPreview() {
    BankPickTheme {
        BankPickIconButton(
            onClick = {},
            imageVector = Icons.Filled.ChevronLeft
        )
    }
}

@Composable
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
private fun BankPickIconButtonPreviewNight() {
    BankPickTheme {
        BankPickIconButton(
            onClick = {},
            imageVector = Icons.Filled.ChevronLeft
        )
    }
}