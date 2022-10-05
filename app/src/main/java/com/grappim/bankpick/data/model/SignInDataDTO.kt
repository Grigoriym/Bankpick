package com.grappim.bankpick.data.model

import kotlinx.serialization.Serializable

@Serializable
data class SignInDataDTO(
    val email:String,
    val password:String
)