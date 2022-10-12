package com.grappim.bankpick.common.ui.auth.signIn

sealed interface SignInResult {
    object Success : SignInResult
    object Error : SignInResult
    object Initial : SignInResult
}