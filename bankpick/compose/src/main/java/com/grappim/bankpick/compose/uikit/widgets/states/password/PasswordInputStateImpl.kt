package com.grappim.bankpick.compose.uikit.widgets.states.password

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.grappim.uikit.R
import com.grappim.bankpick.compose.core.NativeText

class PasswordInputStateImpl(
    initialPassword: String = "",
    initialErrorText: NativeText? = null
) : PasswordInputState {

    private var _password by mutableStateOf(initialPassword)
    private var _errorText: NativeText? by mutableStateOf(initialErrorText)

    override var password: String
        get() = _password
        set(value) {
            _password = value
        }

    override var errorText: NativeText?
        get() = _errorText
        set(value) {
            _errorText = value
        }

    override fun getPasswordErrorText(): NativeText? =
        when {
            password.isEmpty() -> {
                NativeText.Resource(R.string.auth_password_empty)
            }
            isValid.not() -> {
                NativeText.Resource(R.string.auth_password_requirements)
            }
            else -> {
                null
            }
        }

    override val isValid by derivedStateOf { isValidPassword(_password) }

    private fun isValidPassword(password: String): Boolean =
        password.length >= MIN_PASSWORD_LENGTH

    companion object {
        private const val MIN_PASSWORD_LENGTH = 6

        val Empty = PasswordInputStateImpl("")
    }
}