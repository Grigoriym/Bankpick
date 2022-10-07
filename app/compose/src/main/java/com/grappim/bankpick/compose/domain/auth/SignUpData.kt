package com.grappim.bankpick.compose.domain.auth

data class SignUpData(
    val fullName: String,
    val email: String,
    val password: String,
    val phoneNumber: String
)