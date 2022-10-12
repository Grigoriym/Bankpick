package com.grappim.bankpick.compose.core.base

import com.grappim.bankpick.compose.uikit.widgets.states.email.EmailInputState
import com.grappim.bankpick.compose.uikit.widgets.states.email.EmailInputStateImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface EmailStateViewModel {
    val emailState: StateFlow<EmailInputState>
    fun setEmailInput(emailInputState: EmailInputState)
    fun sendEmailError()
}

class EmailStateViewModelImpl : EmailStateViewModel {
    private val _emailState = MutableStateFlow<EmailInputState>(EmailInputStateImpl())
    override val emailState: StateFlow<EmailInputState>
        get() = _emailState.asStateFlow()

    override fun setEmailInput(emailInputState: EmailInputState) {
        _emailState.value = emailInputState
    }

    override fun sendEmailError() {
        val oldEmail = emailState.value
        oldEmail.errorText = emailState.value.getEmailErrorText()
        setEmailInput(oldEmail)
    }
}