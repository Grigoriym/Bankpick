package com.grappim.bankpick.compose.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SignUpDataDTO(
    val fullName: String,
    val email: String,
    val password: String,
    val phoneNumber: String
)