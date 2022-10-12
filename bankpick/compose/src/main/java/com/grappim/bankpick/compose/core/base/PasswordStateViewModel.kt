package com.grappim.bankpick.compose.core.base

import com.grappim.bankpick.compose.uikit.widgets.states.password.PasswordInputState
import com.grappim.bankpick.compose.uikit.widgets.states.password.PasswordInputStateImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface PasswordStateViewModel {
    val passwordState: StateFlow<PasswordInputState>
    fun setPasswordState(passwordInputState: PasswordInputState)
    fun sendPasswordError()
}

class PasswordStateViewModelImpl : PasswordStateViewModel {

    private val _passwordState = MutableStateFlow<PasswordInputState>(PasswordInputStateImpl())
    override val passwordState: StateFlow<PasswordInputState>
        get() = _passwordState.asStateFlow()

    override fun setPasswordState(passwordInputState: PasswordInputState) {
        _passwordState.value = passwordInputState
    }

    override fun sendPasswordError() {
        val oldPassword = passwordState.value
        oldPassword.errorText = passwordState.value.getPasswordErrorText()
        setPasswordState(oldPassword)
    }
}

