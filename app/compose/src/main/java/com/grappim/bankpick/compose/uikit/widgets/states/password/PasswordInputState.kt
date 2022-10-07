package com.grappim.bankpick.compose.uikit.widgets.states.password

import androidx.compose.runtime.Stable
import com.grappim.bankpick.compose.core.NativeText

@Stable
interface PasswordInputState {
    var password: String
    var errorText: NativeText?
    val isValid: Boolean

    fun getPasswordErrorText(): NativeText?
}