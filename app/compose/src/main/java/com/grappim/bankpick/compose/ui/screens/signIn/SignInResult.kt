package com.grappim.bankpick.compose.ui.screens.signIn

sealed interface SignInResult {
    object Success : SignInResult
    object Error : SignInResult
    object Initial : SignInResult
}