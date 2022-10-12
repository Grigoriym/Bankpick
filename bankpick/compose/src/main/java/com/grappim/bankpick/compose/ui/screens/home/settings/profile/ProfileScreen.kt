package com.grappim.bankpick.compose.ui.screens.home.settings.profile

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.grappim.bankpick.common.core.User
import com.grappim.bankpick.compose.ui.theme.BankPickBlackRussian
import com.grappim.bankpick.compose.ui.theme.BankPickLightSlateGrey
import com.grappim.bankpick.compose.ui.theme.BankPickSpunPearl
import com.grappim.bankpick.compose.ui.theme.BankPickTheme
import com.grappim.bankpick.compose.uikit.widgets.BankPickBackButton
import com.grappim.bankpick.compose.uikit.widgets.BankPickDivider
import com.grappim.bankpick.compose.uikit.widgets.BankPickIconButton
import com.grappim.bankpick.compose.uikit.widgets.BankPickTopBarContainer
import com.grappim.uikit.R

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    onBackClicked: () -> Unit,
    onEditProfileClicked: () -> Unit
) {
    val user by viewModel.user.collectAsState()

    Scaffold(
        topBar = {
            ProfileScreenTopBar(
                onBackClicked = onBackClicked,
                onEditProfileClicked = onEditProfileClicked
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(
                    top = 32.dp
                )
        ) {
            ProfileScreenContent(
                user = user
            )
        }
    }
}

@Composable
private fun ProfileScreenContent(
    user: User
) {
    LazyColumn() {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
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
                        .weight(3F)
                ) {
                    val nameColor = if (isSystemInDarkTheme()) {
                        Color.White
                    } else {
                        BankPickBlackRussian
                    }

                    Text(
                        text = user.name,
                        fontSize = 17.sp,
                        color = nameColor
                    )
                    Text(
                        text = user.position,
                        fontSize = 12.sp,
                        color = BankPickLightSlateGrey
                    )
                }
            }
        }
        item {
            Spacer(
                modifier = Modifier
                    .height(32.dp)
            )
        }

        items(profileItems) { item ->
            SettingsItemWidget(profileItem = item)
        }
    }
}

@Composable
private fun ProfileScreenTopBar(
    onBackClicked: () -> Unit,
    onEditProfileClicked: () -> Unit
) {
    BankPickTopBarContainer(
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        BankPickBackButton(
            onClick = onBackClicked
        )
        Text(
            text = stringResource(id = R.string.profile),
            fontSize = 18.sp
        )
        BankPickIconButton(
            onClick = onEditProfileClicked,
            painter = painterResource(id = R.drawable.ic_edit_user)
        )
    }
}

@Composable
fun SettingsItemWidget(
    profileItem: ProfileItem
) {
    Column(
        modifier = Modifier
            .clickable {

            }
    ) {
        Spacer(
            modifier = Modifier
                .height(22.dp)
        )
        Row(
            modifier = Modifier
                .padding(
                    horizontal = 20.dp
                )
        ) {
            Icon(
                painter = painterResource(id = profileItem.icon),
                contentDescription = "",
                modifier = Modifier
                    .weight(1F),
                tint = BankPickSpunPearl
            )
            Text(
                text = profileItem.name,
                fontSize = 14.sp,
                modifier = Modifier
                    .weight(5F)
            )
            Icon(
                imageVector = Icons.Filled.ChevronRight,
                contentDescription = "",
                modifier = Modifier
                    .weight(1F),
                tint = BankPickSpunPearl
            )
        }

        BankPickDivider(
            modifier = Modifier
                .padding(
                    top = 10.dp,
                )
                .padding(
                    horizontal = 20.dp
                )
        )
    }
}

@Composable
@Preview
private fun ProfileScreenContentPreview() {
    BankPickTheme {
        ProfileScreenContent(
            user = User.empty()
        )
    }
}

@Composable
@Preview(
    uiMode = UI_MODE_NIGHT_YES
)
private fun ProfileScreenContentPreviewNight() {
    BankPickTheme {
        ProfileScreenContent(
            user = User.empty()
        )
    }
}

@Composable
@Preview
private fun ProfileScreenTopBarPreview() {
    BankPickTheme {
        ProfileScreenTopBar(
            onBackClicked = {},
            onEditProfileClicked = {}
        )
    }
}

@Composable
@Preview(
    uiMode = UI_MODE_NIGHT_YES
)
private fun ProfileScreenTopBarPreviewNight() {
    BankPickTheme {
        ProfileScreenTopBar(
            onBackClicked = {},
            onEditProfileClicked = {}
        )
    }
}