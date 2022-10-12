package com.grappim.bankpick.domain.model.auth

data class SignUpData(
    val fullName: String,
    val email: String,
    val password: String,
    val phoneNumber: String
)