package com.grappim.bankpick.compose.ui.screens.signUp

sealed interface SignUpResult {
    object Success : SignUpResult
    object Error : SignUpResult
    object Initial : SignUpResult
}