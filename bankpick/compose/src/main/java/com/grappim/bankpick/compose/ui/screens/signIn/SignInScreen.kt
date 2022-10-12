package com.grappim.bankpick.compose.ui.screens.signIn

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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import com.grappim.bankpick.common.ui.auth.signIn.SignInResult
import com.grappim.bankpick.compose.core.NativeText
import com.grappim.bankpick.compose.core.asString
import com.grappim.bankpick.compose.ui.theme.BankPickNavyBlue
import com.grappim.bankpick.compose.ui.theme.BankPickTheme
import com.grappim.bankpick.compose.ui.theme.DefaultHorizontalPadding
import com.grappim.bankpick.compose.uikit.widgets.BankPickBackButton
import com.grappim.bankpick.compose.uikit.widgets.BankPickButton
import com.grappim.bankpick.compose.uikit.widgets.BankPickProgressDialog
import com.grappim.bankpick.compose.uikit.widgets.BankPickSnackbar
import com.grappim.bankpick.compose.uikit.widgets.BankPickTextSubtitle
import com.grappim.bankpick.compose.uikit.widgets.BankPickTopBarContainer
import com.grappim.bankpick.compose.uikit.widgets.states.email.EmailInputState
import com.grappim.bankpick.compose.uikit.widgets.states.email.EmailInputStateImpl
import com.grappim.bankpick.compose.uikit.widgets.states.password.PasswordInputState
import com.grappim.bankpick.compose.uikit.widgets.states.password.PasswordInputStateImpl
import com.grappim.bankpick.compose.uikit.widgets.textField.BankPickEmailTextField
import com.grappim.bankpick.compose.uikit.widgets.textField.BankPickPasswordTextField
import com.grappim.bankpick.compose.utils.LaunchedEffectResult
import com.grappim.uikit.R

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    signInSuccess: () -> Unit,
    onSignUpClick: () -> Unit
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val context = LocalContext.current

    val signInResult by viewModel.signInResult.collectAsState(initial = SignInResult.Initial)
    val snackBarMessage by viewModel.snackBarMessage.collectAsState(
        initial = LaunchedEffectResult(
            data = NativeText.Empty,
            timestamp = 0L
        )
    )
    val loading by viewModel.loading.collectAsState()
    val emailState by viewModel.emailState.collectAsState()
    val passwordState by viewModel.passwordState.collectAsState()

    LaunchedEffect(signInResult) {
        if (signInResult is SignInResult.Success) {
            signInSuccess()
        }
    }
    LaunchedEffect(snackBarMessage) {
        if (snackBarMessage.data !is NativeText.Empty) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = snackBarMessage.data.asString(context)
            )
        }
    }

    BankPickProgressDialog(show = loading)

    SignInScaffold(
        emailInputState = emailState,
        passwordInputState = passwordState,
        scaffoldState = scaffoldState,
        onBackClick = onBackClick,
        onSignInClick = viewModel::signIn,
        onSignUpClick = onSignUpClick
    )
}

@Composable
private fun SignInScaffold(
    emailInputState: EmailInputState,
    passwordInputState: PasswordInputState,
    scaffoldState: ScaffoldState,
    onBackClick: () -> Unit,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    Scaffold(
        scaffoldState = scaffoldState,
        snackbarHost = {
            SnackbarHost(it) { data ->
                BankPickSnackbar(
                    snackbarData = data
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
            emailInputState = emailInputState,
            passwordInputState = passwordInputState,
            paddingValues = paddingValues,
            onSignInClick = onSignInClick,
            onSignUpClick = onSignUpClick
        )
    }
}

@Composable
private fun SignInScreenContent(
    emailInputState: EmailInputState,
    passwordInputState: PasswordInputState,
    paddingValues: PaddingValues,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    val passwordFocusRequester = remember { FocusRequester() }

    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .imePadding()
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
                emailInputState = emailInputState,
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
                passwordInputState = passwordInputState,
                onImeDoneClick = onSignInClick
            )
        }

        item {
            BankPickButton(
                modifier = Modifier
                    .padding(top = 26.dp)
                    .padding(horizontal = DefaultHorizontalPadding),
                text = stringResource(id = R.string.sign_in),
                onClick = onSignInClick
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
        SignInScaffold(
            onSignInClick = {},
            onSignUpClick = {},
            emailInputState = EmailInputStateImpl.Empty,
            passwordInputState = PasswordInputStateImpl.Empty,
            scaffoldState = rememberScaffoldState(),
            onBackClick = {}
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
        SignInScaffold(
            onSignInClick = {},
            onSignUpClick = {},
            emailInputState = EmailInputStateImpl.Empty,
            passwordInputState = PasswordInputStateImpl.Empty,
            scaffoldState = rememberScaffoldState(),
            onBackClick = {}
        )
    }
}