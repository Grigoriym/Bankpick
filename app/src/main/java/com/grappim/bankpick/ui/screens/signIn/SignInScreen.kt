package com.grappim.bankpick.ui.screens.signIn

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.grappim.bankpick.R
import com.grappim.bankpick.core.asString
import com.grappim.bankpick.ui.theme.BankPickBlackRussian
import com.grappim.bankpick.ui.theme.BankPickNavyBlue
import com.grappim.bankpick.ui.theme.BankPickTheme
import com.grappim.bankpick.ui.theme.DefaultHorizontalPadding
import com.grappim.bankpick.uikit.widgets.BankPickBackButton
import com.grappim.bankpick.uikit.widgets.BankPickButton
import com.grappim.bankpick.uikit.widgets.BankPickTextSubtitle
import com.grappim.bankpick.uikit.widgets.BankPickTopBarContainer
import com.grappim.bankpick.uikit.widgets.textField.BankPickEmailTextField
import com.grappim.bankpick.uikit.widgets.textField.BankPickPasswordTextField
import kotlinx.coroutines.CoroutineScope

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val coroutineScope: CoroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val signInResult by viewModel.signInResult.collectAsState(initial = SignInResult.Initial)
    LaunchedEffect(signInResult) {
        if (signInResult is SignInResult.Success) {
            onSignInClick()
        } else if (signInResult is SignInResult.Error
            && (signInResult as SignInResult.Error).generalError != null
        ) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = (signInResult as SignInResult.Error).generalError?.asString(context) ?: ""
            )
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(it) { data ->
                Snackbar(
                    snackbarData = data,
                    contentColor = BankPickBlackRussian
                )
            }
        },
        topBar = {
            BankPickTopBarContainer {
                BankPickBackButton(
                    onClick = onBackClick
                )
            }
        }
    ) { paddingValues ->
        SignInScreenContent(
            paddingValues = paddingValues,
            signInFieldsValidationData = (signInResult as? SignInResult.Error)?.signInFieldsValidationData,
            onSignInClick = { email, password ->
                viewModel.signIn(email, password)
            },
            onSignUpClick = onSignUpClick
        )
    }
}

@Composable
private fun SignInScreenContent(
    paddingValues: PaddingValues,
    signInFieldsValidationData: SignInFieldsValidationData? = null,
    onSignInClick: (email: String, password: String) -> Unit,
    onSignUpClick: () -> Unit
) {
    val listState = rememberLazyListState()
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    val passwordFocusRequester = remember { FocusRequester() }

    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .imePadding(),
        state = listState
    ) {
        item {
            Spacer(
                modifier = Modifier
                    .height(50.dp)
            )
        }
        item {
            Text(
                text = stringResource(id = R.string.sign_in),
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(horizontal = DefaultHorizontalPadding),
                fontSize = 32.sp
            )
        }

        item {
            BankPickEmailTextField(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(horizontal = DefaultHorizontalPadding),
                email = email,
                errorText = signInFieldsValidationData?.emailErrorText,
                onEmailChange = { email = it },
                keyboardActions = KeyboardActions(
                    onNext = {
                        passwordFocusRequester.requestFocus()
                    }
                )
            )
        }

        item {
            BankPickPasswordTextField(
                modifier = Modifier
                    .padding(top = 9.dp)
                    .padding(horizontal = DefaultHorizontalPadding)
                    .focusRequester(passwordFocusRequester),
                password = password,
                errorText = signInFieldsValidationData?.passwordErrorText,
                onPasswordChange = { password = it },
                onImeDoneClick = {
                    onSignInClick(email, password)
                }
            )
        }

        item {
            BankPickButton(
                modifier = Modifier
                    .padding(top = 26.dp)
                    .padding(horizontal = DefaultHorizontalPadding),
                text = stringResource(id = R.string.sign_in),
                onClick = {
                    onSignInClick(email, password)
                }
            )
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = DefaultHorizontalPadding),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                BankPickTextSubtitle(
                    text = stringResource(id = R.string.i_am_new_user)
                )
                TextButton(onClick = onSignUpClick) {
                    BankPickTextSubtitle(
                        text = stringResource(id = R.string.sign_up),
                        color = BankPickNavyBlue
                    )
                }
            }
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
private fun SignInScreenPreview() {
    BankPickTheme {
        SignInScreen(
            onBackClick = {},
            onSignInClick = {},
            onSignUpClick = {}
        )
    }
}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_YES
)
private fun SignInScreenPreviewNight() {
    BankPickTheme {
        SignInScreen(
            onBackClick = {},
            onSignInClick = {},
            onSignUpClick = {}
        )
    }
}