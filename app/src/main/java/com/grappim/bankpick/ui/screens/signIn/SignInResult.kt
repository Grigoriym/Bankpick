package com.grappim.bankpick.ui.screens.signIn

import com.grappim.bankpick.core.NativeText

sealed interface SignInResult {
    object Success : SignInResult
    data class Error(
        val generalError: NativeText? = null,
        val signInFieldsValidationData: SignInFieldsValidationData? = null
    ) : SignInResult

    object Initial : SignInResult
}

data class SignInFieldsValidationData(
    val emailErrorText: NativeText? = null,
    val passwordErrorText: NativeText? = null
) {
    companion object {
        fun empty() =
            SignInFieldsValidationData(
                emailErrorText = null,
                passwordErrorText = null
            )
    }
}