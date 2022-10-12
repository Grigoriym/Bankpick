package com.grappim.bankpick.compose.uikit.widgets.states.email

import com.grappim.bankpick.compose.core.NativeText

interface EmailInputState {
    var email: String
    var errorText: NativeText?
    val isValid: Boolean
    val isValidEmailPattern: Boolean

    fun getEmailErrorText(): NativeText?
}