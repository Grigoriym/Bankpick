package com.grappim.bankpick.compose.uikit.widgets.states.phone

import androidx.compose.runtime.Stable
import com.grappim.bankpick.compose.core.NativeText

@Stable
interface PhoneInputState {
    var phone: String
    var errorText: NativeText?
    val isValid: Boolean

    fun getPhoneErrorText(): NativeText?
}