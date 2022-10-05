package com.grappim.bankpick.ui.screens.home.settings.profile

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.grappim.bankpick.R
import com.grappim.bankpick.core.DateTypePicker
import com.grappim.bankpick.domain.User
import com.grappim.bankpick.ui.theme.BankPickBlackRussian
import com.grappim.bankpick.ui.theme.BankPickLightSlateGrey
import com.grappim.bankpick.ui.theme.BankPickSpunPearl
import com.grappim.bankpick.ui.theme.BankPickTheme
import com.grappim.bankpick.ui.theme.DefaultHorizontalPadding
import com.grappim.bankpick.uikit.widgets.BankPickBackButton
import com.grappim.bankpick.uikit.widgets.BankPickTopBarContainer
import com.grappim.bankpick.uikit.widgets.icon.BankPickDefaultIcon
import com.grappim.bankpick.uikit.widgets.textField.BankPickDefaultTextField
import com.grappim.bankpick.uikit.widgets.textField.BankPickEmailTextField

@Composable
fun EditProfileScreen(
    viewModel: EditProfileViewModel = hiltViewModel(),
    onBackClicked: () -> Unit
) {
    val user by viewModel.user.collectAsState()

    Scaffold(
        topBar = {
            EditProfileTopBar(
                onBackClicked = onBackClicked
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(
                    top = 32.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            EditProfileScreenContent(
                user = user
            )
        }
    }
}

@Composable
private fun EditProfileScreenContent(
    user: User
) {
    Image(
        painter = painterResource(id = R.drawable.ic_logo),
        contentDescription = ""
    )
    val nameColor = if (isSystemInDarkTheme()) {
        Color.White
    } else {
        BankPickBlackRussian
    }
    Text(
        text = user.name,
        modifier = Modifier
            .padding(
                top = 21.dp
            ),
        fontSize = 17.sp,
        color = nameColor
    )
    Text(
        text = user.position,
        modifier = Modifier
            .padding(
                top = 10.dp
            ),
        fontSize = 12.sp,
        color = BankPickLightSlateGrey
    )

    var fullName by rememberSaveable { mutableStateOf("") }
    var phoneNumber by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    LazyColumn(
        modifier = Modifier
            .padding(
                top = 30.dp
            )
    ) {
        item {
            BankPickDefaultTextField(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(horizontal = DefaultHorizontalPadding),
                labelText = stringResource(id = R.string.full_name),
                value = fullName,
                onValueChange = { fullName = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                leadingIcon = {
                    BankPickDefaultIcon(
                        painter = painterResource(id = R.drawable.ic_email),
                    )
                }
            )
        }

        item {
            BankPickEmailTextField(
                modifier = Modifier
                    .padding(top = 9.dp)
                    .padding(horizontal = DefaultHorizontalPadding),
                email = email,
                onEmailChange = { email = it },
//                keyboardActions = KeyboardActions(
//                    onNext = {
//                        focusManager.moveFocus(FocusDirection.Next)
//                    }
//                )
            )
        }

        item {
            BankPickDefaultTextField(
                modifier = Modifier
                    .padding(top = 9.dp)
                    .padding(horizontal = DefaultHorizontalPadding),
                labelText = stringResource(id = R.string.phone_number),
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Phone
                ),
                keyboardActions = KeyboardActions(
                    onNext = {

                    }
                ),
                leadingIcon = {
                    BankPickDefaultIcon(
                        painter = painterResource(id = R.drawable.ic_phone),
                    )
                }
            )
        }

        item {
            Text(
                text = stringResource(id = R.string.birth_date),
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(horizontal = DefaultHorizontalPadding),
                color = BankPickSpunPearl
            )
        }
        item {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {

            }
        }
    }
}

@Composable
private fun EditProfileTopBar(
    onBackClicked: () -> Unit
) {
    BankPickTheme {
        BankPickTopBarContainer(
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            BankPickBackButton(
                onClick = onBackClicked
            )
            Text(
                text = stringResource(R.string.edit_profile),
                fontSize = 18.sp
            )
            Spacer(modifier = Modifier.size(42.dp))
        }
    }
}

@Composable
private fun ItemPicker(
    modifier: Modifier = Modifier,
    type: DateTypePicker
) {
    Box(modifier = modifier) {
        LazyColumn() {

        }
    }
}

@Composable
@Preview
private fun EditProfileTopBarPreview() {
    BankPickTheme {
        EditProfileTopBar(
            onBackClicked = {}
        )
    }
}

@Composable
@Preview(
    uiMode = UI_MODE_NIGHT_YES
)
private fun EditProfileTopBarPreviewNight() {
    BankPickTheme {
        EditProfileTopBar(
            onBackClicked = {}
        )
    }
}

@Composable
@Preview
private fun EditProfileScreenContentPreview() {
    BankPickTheme {
        EditProfileScreenContent(
            user = User.empty()
        )
    }
}

@Composable
@Preview(
    uiMode = UI_MODE_NIGHT_YES
)
private fun EditProfileScreenContentPreviewNight() {
    BankPickTheme {
        EditProfileScreenContent(
            user = User.empty()
        )
    }
}