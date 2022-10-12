package com.grappim.bankpick.domain.model.auth

data class SignInDataError(
    val emailError: String? = null,
    val passwordError: String? = null
)