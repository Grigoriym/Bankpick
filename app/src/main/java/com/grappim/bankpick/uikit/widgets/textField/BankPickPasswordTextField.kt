package com.grappim.bankpick.uikit.widgets.textField

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.grappim.bankpick.R
import com.grappim.bankpick.core.NativeText
import com.grappim.bankpick.ui.theme.BankPickSpunPearl
import com.grappim.bankpick.ui.theme.BankPickTheme
import com.grappim.bankpick.uikit.widgets.icon.BankPickDefaultIcon

@Composable
fun BankPickPasswordTextField(
    modifier: Modifier = Modifier,
    password: String,
    errorText: NativeText? = null,
    onPasswordChange: (String) -> Unit,
    onImeDoneClick: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var passwordVisibility by remember {
        mutableStateOf(false)
    }

    BankPickDefaultTextField(
        modifier = modifier,
        labelText = stringResource(id = R.string.password),
        value = password,
        errorText = errorText,
        onValueChange = onPasswordChange,
        leadingIcon = {
            BankPickDefaultIcon(
                painter = painterResource(id = R.drawable.ic_password),
                tint = BankPickSpunPearl
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onImeDoneClick()
                keyboardController?.hide()
            }
        ),
        visualTransformation = if (passwordVisibility) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        trailingIcon = {
            val image = if (passwordVisibility) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }
            IconButton(
                onClick = { passwordVisibility = !passwordVisibility }
            ) {
                BankPickDefaultIcon(
                    imageVector = image,
                    tint = BankPickSpunPearl
                )
            }
        }
    )
}

@Composable
@Preview
private fun BankPickPasswordTextFieldPreview() {
    BankPickTheme {
        BankPickPasswordTextField(
            password = "asd",
            onPasswordChange = {},
            onImeDoneClick = {}
        )
    }
}