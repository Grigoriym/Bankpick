package com.grappim.bankpick.compose.uikit.widgets.textField

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.grappim.bankpick.compose.R
import com.grappim.bankpick.compose.ui.theme.BankPickTheme
import com.grappim.bankpick.compose.uikit.widgets.icon.BankPickDefaultIcon
import com.grappim.bankpick.compose.uikit.widgets.states.email.EmailInputState
import com.grappim.bankpick.compose.uikit.widgets.states.email.EmailInputStateImpl

@Composable
fun BankPickEmailTextField(
    modifier: Modifier = Modifier,
    emailInputState: EmailInputState,
    imeAction: ImeAction = ImeAction.Next,
    keyboardActions: KeyboardActions = KeyboardActions(),
) {
    BankPickDefaultTextField(
        modifier = modifier,
        labelText = stringResource(id = R.string.email_address),
        value = emailInputState.email,
        errorText = emailInputState.errorText,
        isError = !emailInputState.isValid && emailInputState.errorText != null,
        onValueChange = { value ->
            emailInputState.email = value
        },
        leadingIcon = {
            BankPickDefaultIcon(
                painter = painterResource(id = R.drawable.ic_email)
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = imeAction,
            keyboardType = KeyboardType.Email
        ),
        keyboardActions = keyboardActions
    )
}

@Composable
@Preview(
    showBackground = true
)
private fun BankPickEmailTextFieldPreview() {
    BankPickTheme {
        BankPickEmailTextField(
            emailInputState = EmailInputStateImpl("asd")
        )
    }
}

@Composable
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES
)
private fun BankPickEmailTextFieldPreviewNight() {
    BankPickTheme {
        BankPickEmailTextField(
            emailInputState = EmailInputStateImpl("asd")
        )
    }
}