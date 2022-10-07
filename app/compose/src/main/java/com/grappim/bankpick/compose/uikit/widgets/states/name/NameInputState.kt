package com.grappim.bankpick.compose.uikit.widgets.states.name

import com.grappim.bankpick.compose.core.NativeText

interface NameInputState {
    var name: String
    var errorText: NativeText?
    val isValid: Boolean

    fun getNameErrorText(): NativeText?
}