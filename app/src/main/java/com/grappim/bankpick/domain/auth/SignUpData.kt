package com.grappim.bankpick.domain.auth

data class SignUpData(
    val fullName: String,
    val email: String,
    val password: String,
    val phoneNumber: String
)