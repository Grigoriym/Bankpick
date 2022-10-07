package com.grappim.bankpick.compose.ui.screens.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.grappim.bankpick.compose.R
import com.grappim.bankpick.compose.domain.Transaction
import com.grappim.bankpick.compose.domain.User
import com.grappim.bankpick.compose.ui.theme.BankPickBlackRussian
import com.grappim.bankpick.compose.ui.theme.BankPickSpunPearl
import com.grappim.bankpick.compose.ui.theme.BankPickTheme
import com.grappim.bankpick.compose.ui.theme.DefaultHorizontalPadding
import com.grappim.bankpick.compose.uikit.widgets.BankPickCardWidget
import com.grappim.bankpick.compose.uikit.widgets.BankPickTopBarContainer
import com.grappim.bankpick.compose.uikit.widgets.TransactionWidget
import com.grappim.bankpick.compose.uikit.widgets.icon.BankPickIconWithBottomText

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onSeeAllClick: () -> Unit,
    onTransactionClick: (Transaction) -> Unit
) {
    val user by viewModel.user.collectAsState()
    Scaffold(
        topBar = {
            HomeScreenTopBar(user)
        }
    ) { paddingValues ->
        HomeScreenContent(
            paddingValues = paddingValues,
            user = user,
            onSeeAllClick = onSeeAllClick,
            onTransactionClick = onTransactionClick
        )
    }
}

@Composable
private fun HomeScreenTopBar(
    user: User
) {
    BankPickTopBarContainer {
        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "",
            modifier = Modifier
                .size(50.dp)
                .weight(1F)
        )

        Column(
            modifier = Modifier
                .padding(
                    start = 16.dp
                )
                .weight(5F)
        ) {
            Text(
                text = stringResource(id = R.string.welcome_back),
                fontSize = 12.sp,
                color = BankPickSpunPearl
            )
            val textColor = if (isSystemInDarkTheme()) {
                Color.White
            } else {
                BankPickBlackRussian
            }
            Text(
                text = user.name,
                fontSize = 18.sp,
                color = textColor
            )
        }
    }
}

@Composable
private fun HomeScreenContent(
    paddingValues: PaddingValues,
    user: User,
    onSeeAllClick: () -> Unit,
    onTransactionClick: (Transaction) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
    ) {
        BankPickCardWidget(
            modifier = Modifier
                .padding(
                    top = 32.dp
                )
                .padding(horizontal = 20.dp)
                .align(CenterHorizontally),
            cardData = user.cardData
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 30.dp
                )
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BankPickIconWithBottomText(
                imageVector = Icons.Filled.ArrowUpward,
                text = stringResource(id = R.string.send),
                onClick = {}
            )
            BankPickIconWithBottomText(
                imageVector = Icons.Filled.ArrowDownward,
                text = stringResource(id = R.string.receive),
                onClick = {}
            )
            BankPickIconWithBottomText(
                painter = painterResource(id = R.drawable.ic_loan),
                text = stringResource(id = R.string.loan),
                onClick = {}
            )
            BankPickIconWithBottomText(
                painter = painterResource(id = R.drawable.ic_topup),
                text = stringResource(id = R.string.top_up),
                onClick = {}
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 28.dp
                )
                .padding(horizontal = DefaultHorizontalPadding)
        ) {
            Text(
                text = stringResource(id = R.string.transaction),
                fontSize = 18.sp
            )

            TextButton(onClick = onSeeAllClick) {
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
                    onClick = onTransactionClick
                )
            }
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
private fun HomeScreenContentPreview() {
    BankPickTheme {
        HomeScreenContent(
            paddingValues = PaddingValues(),
            user = User.empty(),
            onSeeAllClick = {},
            onTransactionClick = {}
        )
    }
}

@Composable
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
    showSystemUi = true
)
private fun HomeScreenPreviewContentNight() {
    BankPickTheme {
        HomeScreenContent(
            paddingValues = PaddingValues(),
            user = User.empty(),
            onSeeAllClick = {},
            onTransactionClick = {}
        )
    }
}

@Composable
@Preview
private fun HomeScreenContentTopBarPreview() {
    BankPickTheme {
        HomeScreenTopBar(User.empty())
    }
}

@Composable
@Preview(
    uiMode = UI_MODE_NIGHT_YES,
)
private fun HomeScreenContentTopBarPreviewNight() {
    BankPickTheme {
        HomeScreenTopBar(User.empty())
    }
}