package com.grappim.bankpick.compose.uikit.widgets.states.phone

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.grappim.bankpick.compose.R
import com.grappim.bankpick.compose.core.NativeText

class PhoneInputStateImpl(
    initialPhone: String = "",
    initialErrorText: NativeText? = null
) : PhoneInputState {

    private var _phone by mutableStateOf(initialPhone)
    private var _errorText: NativeText? by mutableStateOf(initialErrorText)

    override var phone: String
        get() = _phone
        set(value) {
            _phone = value
        }

    override var errorText: NativeText?
        get() = _errorText
        set(value) {
            _errorText = value
        }

    override val isValid: Boolean by derivedStateOf {
        _phone.isNotEmpty()
    }

    override fun getPhoneErrorText(): NativeText? =
        when {
            _phone.isEmpty() -> {
                NativeText.Resource(R.string.phone_empty)
            }
            else -> null
        }

    companion object {
        val Empty = PhoneInputStateImpl()
    }
}