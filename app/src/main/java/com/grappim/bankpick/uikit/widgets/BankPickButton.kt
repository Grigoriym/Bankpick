package com.grappim.bankpick.uikit.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grappim.bankpick.ui.theme.BankPickNavyBlue
import com.grappim.bankpick.ui.theme.BankPickTheme

@Composable
fun BankPickButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = BankPickNavyBlue,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(size = 16.dp)
    ) {
        Text(
            text = text,
            fontSize = 16.sp
        )
    }
}

@Composable
@Preview(
    showBackground = true
)
private fun BankPickButtonPreview() {
    BankPickTheme {
        BankPickButton(
            text = "Next",
            onClick = {})
    }
}