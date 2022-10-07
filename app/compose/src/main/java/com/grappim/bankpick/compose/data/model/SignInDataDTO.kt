package com.grappim.bankpick.compose.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SignInDataDTO(
    val email:String,
    val password:String
)