package com.grappim.bankpick.compose.ui.screens.home.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grappim.bankpick.compose.R
import com.grappim.bankpick.compose.ui.theme.BankPickSpunPearl
import com.grappim.bankpick.compose.ui.theme.BankPickTheme
import com.grappim.bankpick.compose.uikit.widgets.BankPickBackButton
import com.grappim.bankpick.compose.uikit.widgets.BankPickIconButton
import com.grappim.bankpick.compose.uikit.widgets.BankPickTopBarContainer

@Composable
fun SettingsScreen(
    onSettingsItemClicked: (SettingsItem) -> Unit
) {
    Scaffold(
        topBar = {
            BankPickTopBarContainer(
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                BankPickBackButton(
                    onClick = {}
                )
                Text(
                    text = "Settings",
                    fontSize = 18.sp
                )
                BankPickIconButton(
                    onClick = { },
                    painter = painterResource(id = R.drawable.ic_exit)
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(
                    top = 32.dp
                )
        ) {
            item {
                Text(
                    text = "General",
                    fontSize = 14.sp,
                    color = BankPickSpunPearl,
                    modifier = Modifier
                        .padding(
                            horizontal = 20.dp
                        )
                )
            }
            item {
                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                )
            }

            items(generalSettingsItems) { item ->
                SettingsItemWidget(
                    item = item,
                    onItemClicked = onSettingsItemClicked
                )
            }

            item {
                Text(
                    text = "Security",
                    fontSize = 14.sp,
                    color = BankPickSpunPearl,
                    modifier = Modifier
                        .padding(
                            horizontal = 20.dp
                        )
                        .padding(
                            top = 30.dp
                        )
                )
            }

            items(securitySettingsItems) { item ->
                SettingsItemWidget(
                    item = item,
                    onItemClicked = onSettingsItemClicked
                )
            }
        }
    }
}

@Composable
private fun SettingsItemWidget(
    item: SettingsItem,
    onItemClicked: (SettingsItem) -> Unit
) {
    Column(
        modifier = Modifier
            .clickable {
                onItemClicked(item)
            }
            .padding(
                horizontal = 20.dp
            )
            .fillMaxWidth()
    ) {
        Spacer(
            modifier = Modifier
                .height(22.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = item.name,
                fontSize = 14.sp
            )
            Icon(imageVector = Icons.Filled.ChevronRight, contentDescription = "")
        }
        Divider(
            modifier = Modifier
                .padding(
                    top = 10.dp
                )
        )
    }
}

@Composable
@Preview
private fun SettingsScreenPreview() {
    BankPickTheme {
        SettingsScreen(
            onSettingsItemClicked = {}
        )
    }
}