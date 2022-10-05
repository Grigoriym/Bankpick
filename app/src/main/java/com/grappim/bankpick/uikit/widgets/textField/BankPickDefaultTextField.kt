package com.grappim.bankpick.uikit.widgets.textField

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grappim.bankpick.core.NativeText
import com.grappim.bankpick.core.asString
import com.grappim.bankpick.ui.theme.BankPickBlackRussian2
import com.grappim.bankpick.ui.theme.BankPickNavyBlue
import com.grappim.bankpick.ui.theme.BankPickTheme
import com.grappim.bankpick.ui.theme.BankPickWhiteSmoke1
import com.grappim.bankpick.uikit.widgets.BankPickTextSubtitle

@Composable
fun BankPickDefaultTextField(
    modifier: Modifier = Modifier,
    labelText: String,
    value: String,
    errorText: NativeText? = null,
    onValueChange: (String) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        BankPickTextSubtitle(
            text = labelText,
            modifier = Modifier
                .padding(top = 20.dp),
            isError = errorText != null
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            value = value,
            isError = errorText != null,
            onValueChange = onValueChange,
            leadingIcon = leadingIcon,
            singleLine = true,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            trailingIcon = trailingIcon,
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = BankPickNavyBlue,
                unfocusedIndicatorColor = unfocusedIndicatorColor()
            )
        )

        val error = errorText?.asString(LocalContext.current)
        val errorVisible = if (error?.isNotEmpty() == true) 1f else 0f

        Text(
            text = error ?: "",
            color = MaterialTheme.colors.error,
            modifier = Modifier
                .padding(
                    top = 8.dp,
                    start = 16.dp
                )
                .alpha(errorVisible)
        )
    }
}

@Composable
private fun unfocusedIndicatorColor(): Color =
    if (isSystemInDarkTheme()) {
        BankPickBlackRussian2
    } else {
        BankPickWhiteSmoke1
    }

@Composable
@Preview(
    showBackground = true,
    name = "Default Light"
)
private fun BankPickDefaultTextFieldPreview() {
    BankPickTheme {
        BankPickDefaultTextField(
            labelText = "email",
            onValueChange = {},
            value = "asd"
        )
    }
}

@Composable
@Preview(
    showBackground = true,
    name = "Error Light"
)
private fun BankPickDefaultTextFieldErrorPreview() {
    BankPickTheme {
        BankPickDefaultTextField(
            labelText = "email",
            onValueChange = {},
            value = "asd",
            errorText = NativeText.Simple("Password is empty")
        )
    }
}

@Composable
@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_YES,
    name = "Default Night"
)
private fun BankPickDefaultTextFieldPreviewNight() {
    BankPickTheme {
        BankPickDefaultTextField(
            labelText = "email",
            onValueChange = {},
            value = "asd"
        )
    }
}