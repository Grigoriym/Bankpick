package com.grappim.bankpick.data.model.auth

import kotlinx.serialization.Serializable

@Serializable
data class SignUpDataDTO(
    val fullName: String,
    val email: String,
    val password: String,
    val phoneNumber: String
)