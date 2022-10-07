package com.grappim.bankpick.compose.ui.screens.signUp

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.grappim.bankpick.compose.R
import com.grappim.bankpick.compose.core.NativeText
import com.grappim.bankpick.compose.core.asString
import com.grappim.bankpick.compose.ui.theme.BankPickSpunPearl
import com.grappim.bankpick.compose.ui.theme.BankPickTheme
import com.grappim.bankpick.compose.ui.theme.DefaultHorizontalPadding
import com.grappim.bankpick.compose.uikit.widgets.BankPickBackButton
import com.grappim.bankpick.compose.uikit.widgets.BankPickButton
import com.grappim.bankpick.compose.uikit.widgets.BankPickProgressDialog
import com.grappim.bankpick.compose.uikit.widgets.BankPickSnackbar
import com.grappim.bankpick.compose.uikit.widgets.BankPickTopBarContainer
import com.grappim.bankpick.compose.uikit.widgets.icon.BankPickDefaultIcon
import com.grappim.bankpick.compose.uikit.widgets.states.email.EmailInputState
import com.grappim.bankpick.compose.uikit.widgets.states.email.EmailInputStateImpl
import com.grappim.bankpick.compose.uikit.widgets.states.name.NameInputState
import com.grappim.bankpick.compose.uikit.widgets.states.name.NameInputStateImpl
import com.grappim.bankpick.compose.uikit.widgets.states.password.PasswordInputState
import com.grappim.bankpick.compose.uikit.widgets.states.password.PasswordInputStateImpl
import com.grappim.bankpick.compose.uikit.widgets.states.phone.PhoneInputState
import com.grappim.bankpick.compose.uikit.widgets.states.phone.PhoneInputStateImpl
import com.grappim.bankpick.compose.uikit.widgets.textField.BankPickDefaultTextField
import com.grappim.bankpick.compose.uikit.widgets.textField.BankPickEmailTextField
import com.grappim.bankpick.compose.uikit.widgets.textField.BankPickPasswordTextField
import com.grappim.bankpick.compose.utils.LaunchedEffectResult
import com.grappim.bankpick.compose.utils.PositionHandler
import com.grappim.bankpick.compose.utils.PositionManager

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
    onSignInClick: () -> Unit,
    signUpSuccess: () -> Unit
) {
    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val context = LocalContext.current

    val signUpResult by viewModel
        .signUpResult
        .collectAsState(initial = SignUpResult.Initial)
    val snackBarMessage by viewModel.snackBarMessage.collectAsState(
        initial = LaunchedEffectResult(
            data = NativeText.Empty,
            timestamp = 0L
        )
    )

    LaunchedEffect(signUpResult) {
        if (signUpResult is SignUpResult.Success) {
            signUpSuccess()
        }
    }
    LaunchedEffect(snackBarMessage) {
        if (snackBarMessage.data !is NativeText.Empty) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = snackBarMessage.data.asString(context)
            )
        }
    }

    val loading by viewModel.loading
    val emailInputState by viewModel.emailState.collectAsState()
    val passwordInputState by viewModel.passwordState.collectAsState()
    val nameInputState by viewModel.nameState.collectAsState()
    val phoneInputState by viewModel.phoneState.collectAsState()

    SignUpScaffold(
        loading = loading,
        emailInputState = emailInputState,
        passwordInputState = passwordInputState,
        nameInputState = nameInputState,
        phoneInputState = phoneInputState,
        scaffoldState = scaffoldState,
        onBackClick = onBackClick,
        onSignInClick = onSignInClick,
        onSignUpClick = viewModel::signUp
    )
}

@Composable
private fun SignUpScaffold(
    loading: Boolean,
    emailInputState: EmailInputState,
    passwordInputState: PasswordInputState,
    nameInputState: NameInputState,
    phoneInputState: PhoneInputState,
    scaffoldState: ScaffoldState,
    onBackClick: () -> Unit,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
) {
    ProvideWindowInsets {
        BankPickProgressDialog(show = loading)

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
            SignUpScreenContent(
                paddingValues = paddingValues,
                emailInputState = emailInputState,
                passwordInputState = passwordInputState,
                nameInputState = nameInputState,
                phoneInputState = phoneInputState,
                onSignInClick = onSignInClick,
                onSignUpClick = onSignUpClick
            )
        }
    }
}

@Composable
private fun SignUpScreenContent(
    paddingValues: PaddingValues,
    emailInputState: EmailInputState,
    passwordInputState: PasswordInputState,
    nameInputState: NameInputState,
    phoneInputState: PhoneInputState,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    val listState = rememberLazyListState()

    val phoneNumberFocusRequester = remember { FocusRequester() }
    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }

    val positionManager = remember {
        PositionManager()
    }
    val insets = LocalWindowInsets.current
    val ime = insets.ime
    val keyboardSize = ime.layoutInsets.bottom
    positionManager.setCurrentHeight(keyboardSize)

    var focusIndex by remember {
        mutableStateOf(-1)
    }
    if (focusIndex != -1 && ime.isVisible) {
        LaunchedEffect(key1 = keyboardSize, ime.isVisible) {
            val amount = positionManager.getScrollAmount(focusIndex) -
                    insets.navigationBars.bottom
            if (amount > 0) listState.scrollBy(amount)
        }
    }

    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .navigationBarsWithImePadding()
            .onGloballyPositioned {
                if (!positionManager.isParentSet)
                    positionManager.setupParent(it)
            },
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
                text = stringResource(id = R.string.sign_up),
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(horizontal = DefaultHorizontalPadding),
                fontSize = 32.sp
            )
        }

        item {
            BankPickDefaultTextField(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(horizontal = DefaultHorizontalPadding)
                    .onGloballyPositioned {
                        if (!ime.animationInProgress || ime.animationInProgress && 1 ==
                            focusIndex
                        ) {
                            positionManager.registerPosition(
                                1,
                                PositionHandler(it)
                            )
                        }
                    }
                    .onFocusChanged {
                        if (it.isFocused) {
                            focusIndex = 1
                        }
                    },
                labelText = stringResource(id = R.string.full_name),
                value = nameInputState.name,
                onValueChange = { nameInputState.name = it },
                errorText = nameInputState.errorText,
                isError = nameInputState.isValid.not() && nameInputState.errorText != null,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        phoneNumberFocusRequester.requestFocus()
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
            BankPickDefaultTextField(
                modifier = Modifier
                    .padding(top = 9.dp)
                    .padding(horizontal = DefaultHorizontalPadding)
                    .focusRequester(phoneNumberFocusRequester)
                    .onGloballyPositioned {
                        if (!ime.animationInProgress || ime.animationInProgress && 2 ==
                            focusIndex
                        ) {
                            positionManager.registerPosition(
                                2,
                                PositionHandler(it)
                            )
                        }
                    }
                    .onFocusChanged {
                        if (it.isFocused) {
                            focusIndex = 2
                        }
                    },
                labelText = stringResource(id = R.string.phone_number),
                value = phoneInputState.phone,
                onValueChange = { phoneInputState.phone = it },
                errorText = phoneInputState.errorText,
                isError = phoneInputState.isValid.not() && phoneInputState.errorText != null,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Phone
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        emailFocusRequester.requestFocus()
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
            BankPickEmailTextField(
                modifier = Modifier
                    .padding(top = 9.dp)
                    .padding(horizontal = DefaultHorizontalPadding)
                    .focusRequester(emailFocusRequester)
                    .onGloballyPositioned {
                        if (!ime.animationInProgress || ime.animationInProgress && 3 ==
                            focusIndex
                        ) {
                            positionManager.registerPosition(
                                3,
                                PositionHandler(it)
                            )
                        }
                    }
                    .onFocusChanged {
                        if (it.isFocused) {
                            focusIndex = 3
                        }
                    },
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
                    .focusRequester(passwordFocusRequester)
                    .onGloballyPositioned {
                        if (!ime.animationInProgress || ime.animationInProgress && 4 ==
                            focusIndex
                        ) {
                            positionManager.registerPosition(
                                4,
                                PositionHandler(it)
                            )
                        }
                    }
                    .onFocusChanged {
                        if (it.isFocused) {
                            focusIndex = 4
                        }
                    },
                passwordInputState = passwordInputState,
                onImeDoneClick = onSignUpClick
            )
        }
        item {
            BankPickButton(
                modifier = Modifier
                    .padding(top = 26.dp)
                    .padding(horizontal = DefaultHorizontalPadding),
                text = stringResource(id = R.string.sign_up),
                onClick = onSignUpClick
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
                Text(
                    text = stringResource(id = R.string.already_have_account),
                    color = BankPickSpunPearl
                )
                TextButton(onClick = onSignInClick) {
                    Text(text = stringResource(id = R.string.sign_in))
                }
            }
        }
        item {
            Spacer(
                modifier = Modifier
                    .height(with(LocalDensity.current) {
                        (keyboardSize - ime.bottom).toDp()
                    })
            )
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
private fun SignUpScreenPreview() {
    BankPickTheme {
        SignUpScaffold(
            loading = false,
            emailInputState = EmailInputStateImpl.Empty,
            passwordInputState = PasswordInputStateImpl.Empty,
            nameInputState = NameInputStateImpl.Empty,
            phoneInputState = PhoneInputStateImpl.Empty,
            scaffoldState = rememberScaffoldState(),
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
private fun SignUpScreenPreviewNight() {
    BankPickTheme {
        SignUpScaffold(
            loading = false,
            emailInputState = EmailInputStateImpl.Empty,
            passwordInputState = PasswordInputStateImpl.Empty,
            nameInputState = NameInputStateImpl.Empty,
            phoneInputState = PhoneInputStateImpl.Empty,
            scaffoldState = rememberScaffoldState(),
            onBackClick = {},
            onSignInClick = {},
            onSignUpClick = {}
        )
    }
}