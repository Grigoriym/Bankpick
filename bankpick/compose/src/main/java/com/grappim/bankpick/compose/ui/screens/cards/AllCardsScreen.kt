package com.grappim.bankpick.compose.ui.screens.cards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.grappim.bankpick.common.core.User
import com.grappim.bankpick.compose.uikit.widgets.BankPickBackButton
import com.grappim.bankpick.compose.uikit.widgets.BankPickTopBarContainer
import com.grappim.uikit.R

@Composable
fun AllCardsScreen(
    viewModel: AllCardsViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
) {
    val user by viewModel.user.collectAsState()
    Scaffold(
        topBar = {
            AllCardsScreenTopBar(
                onBackPressed = onBackPressed
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
//            AllCardsScreenContent(
//                user = user
//            )
        }
    }
}

@Composable
private fun AllCardsScreenTopBar(
    onBackPressed: () -> Unit
) {
    BankPickTopBarContainer(
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        BankPickBackButton(
            onClick = onBackPressed
        )
        Text(
            text = stringResource(id = R.string.all_cards),
            fontSize = 18.sp
        )
        Spacer(
            modifier = Modifier
                .size(42.dp)
        )
    }
}

@Composable
private fun AllCardsScreenContent(
    user: User
) {

}