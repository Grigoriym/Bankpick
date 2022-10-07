package com.grappim.bankpick.compose.ui.screens.home

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grappim.bankpick.compose.R
import com.grappim.bankpick.compose.domain.Transaction
import com.grappim.bankpick.compose.ui.theme.BankPickBlackRussian
import com.grappim.bankpick.compose.ui.theme.BankPickSpunPearl
import com.grappim.bankpick.compose.ui.theme.BankPickTheme
import com.grappim.bankpick.compose.uikit.widgets.BankPickBackButton
import com.grappim.bankpick.compose.uikit.widgets.BankPickChartWidget
import com.grappim.bankpick.compose.uikit.widgets.BankPickIconButton
import com.grappim.bankpick.compose.uikit.widgets.BankPickTopBarContainer
import com.grappim.bankpick.compose.uikit.widgets.TransactionWidget

@Composable
fun StatisticsScreen(
    onBackClicked: () -> Unit,
    onNotificationsClicked: () -> Unit,
    onSeeAllClicked: () -> Unit,
    onTransactionClicked: (Transaction) -> Unit
) {
    Scaffold(
        topBar = {
            StatisticsTopBar(
                onBackClicked = onBackClicked,
                onNotificationsClicked = onNotificationsClicked
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(
                    top = 31.dp
                )
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val sumColor = if (isSystemInDarkTheme()) {
                Color.White
            } else {
                BankPickBlackRussian
            }
            Text(
                text = stringResource(id = R.string.current_balance),
                fontSize = 18.sp,
                color = BankPickSpunPearl
            )
            Text(
                text = "$8,545.00",
                fontSize = 26.sp,
                color = sumColor,
                modifier = Modifier
                    .padding(
                        top = 12.dp
                    )
            )

            BankPickChartWidget(
                modifier = Modifier
                    .padding(
                        top = 30.dp
                    )
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 28.dp
                    )
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.transaction),
                    fontSize = 18.sp
                )

                TextButton(onClick = onSeeAllClicked) {
                    Text(
                        text = stringResource(id = R.string.see_all),
                        fontSize = 14.sp
                    )
                }
            }

            LazyColumn() {
                items(Transaction.transactionItems()) { item ->
                    TransactionWidget(
                        transaction = item,
                        onClick = onTransactionClicked
                    )
                }
            }
        }
    }
}

@Composable
private fun StatisticsTopBar(
    onBackClicked: () -> Unit,
    onNotificationsClicked: () -> Unit,
) {
    BankPickTopBarContainer(
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        BankPickBackButton(
            onClick = onBackClicked
        )
        Text(
            text = stringResource(id = R.string.statistics),
            fontSize = 18.sp
        )
        BankPickIconButton(
            onClick = onNotificationsClicked,
            imageVector = Icons.Outlined.Notifications
        )
    }
}

@Composable
@Preview
private fun StatisticsScreenPreview() {
    BankPickTheme {
        StatisticsScreen(
            onBackClicked = {},
            onNotificationsClicked = {},
            onSeeAllClicked = {},
            onTransactionClicked = {}
        )
    }
}