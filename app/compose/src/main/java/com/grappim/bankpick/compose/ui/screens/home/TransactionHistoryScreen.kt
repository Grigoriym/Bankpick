package com.grappim.bankpick.compose.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grappim.uikit.R
import com.grappim.bankpick.compose.domain.Transaction
import com.grappim.bankpick.compose.ui.theme.BankPickTheme
import com.grappim.bankpick.compose.uikit.widgets.BankPickBackButton
import com.grappim.bankpick.compose.uikit.widgets.BankPickTopBarContainer
import com.grappim.bankpick.compose.uikit.widgets.TransactionWidget

@Composable
fun TransactionHistoryScreen(
    onBackPressed: () -> Unit,
    onTransactionClick: (Transaction) -> Unit
) {
    Scaffold(
        topBar = {
            TransactionHistoryTopBar(
                onBackPressed = onBackPressed
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            TransactionHistoryContent(onTransactionClick = onTransactionClick)
        }
    }
}

@Composable
private fun TransactionHistoryContent(
    onTransactionClick: (Transaction) -> Unit
) {
    LazyColumn() {
        items(Transaction.transactionItems()) { item ->
            TransactionWidget(
                transaction = item,
                onClick = onTransactionClick
            )
        }
    }
}

@Composable
private fun TransactionHistoryTopBar(
    onBackPressed: () -> Unit
) {
    BankPickTopBarContainer(
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        BankPickBackButton(
            onClick = onBackPressed
        )
        Text(
            text = stringResource(id = R.string.transaction_history),
            fontSize = 18.sp
        )
        Spacer(
            modifier = Modifier
                .size(42.dp)
        )
    }
}

@Composable
@Preview
private fun TransactionHistoryTopBarPreview() {
    BankPickTheme {
        TransactionHistoryTopBar(
            onBackPressed = {}
        )
    }
}