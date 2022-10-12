package com.grappim.bankpick.compose.uikit.widgets

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grappim.bankpick.compose.ui.theme.BankPickBlackRussian
import com.grappim.bankpick.compose.ui.theme.BankPickDimGray
import com.grappim.bankpick.compose.ui.theme.BankPickSpunPearl
import com.grappim.bankpick.compose.ui.theme.BankPickTheme
import com.grappim.bankpick.compose.ui.theme.DefaultHorizontalPadding
import com.grappim.bankpick.domain.model.transaction.Transaction
import com.grappim.uikit.R

@Composable
fun TransactionWidget(
    transaction: Transaction,
    onClick: (Transaction) -> Unit
) {
    Row(
        modifier = Modifier
            .clickable {
                onClick(transaction)
            }
            .padding(
                top = 8.dp,
            )
            .fillMaxWidth()
            .padding(
                horizontal = DefaultHorizontalPadding
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "",
            modifier = Modifier
                .size(42.dp)
                .weight(1F)
        )

        val titleColor = if (isSystemInDarkTheme()) {
            Color.White
        } else {
            BankPickBlackRussian
        }
        val detailsColor = if (isSystemInDarkTheme()) {
            BankPickDimGray
        } else {
            BankPickSpunPearl
        }
        Column(
            modifier = Modifier
                .padding(
                    start = 16.dp
                )
                .weight(3F)
        ) {
            Text(
                text = transaction.name,
                fontSize = 16.sp,
                color = titleColor
            )
            Text(
                text = transaction.category,
                fontSize = 12.sp,
                color = detailsColor
            )
        }

        Text(
            modifier = Modifier
                .weight(3F),
            text = transaction.sum,
            textAlign = TextAlign.End
        )
    }
}

@Composable
@Preview(
    showBackground = true
)
private fun TransactionWidgetPreview() {
    BankPickTheme {
        TransactionWidget(
            transaction = Transaction.empty(),
            onClick = {}
        )
    }
}

@Composable
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
private fun TransactionWidgetPreviewNight() {
    BankPickTheme {
        TransactionWidget(
            transaction = Transaction.empty(),
            onClick = {}
        )
    }
}