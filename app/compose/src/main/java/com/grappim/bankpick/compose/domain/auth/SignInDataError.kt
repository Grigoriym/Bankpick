package com.grappim.bankpick.compose.domain.auth

data class SignInDataError(
    val emailError: String? = null,
    val passwordError: String? = null
)