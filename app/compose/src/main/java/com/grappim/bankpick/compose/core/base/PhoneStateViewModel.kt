package com.grappim.bankpick.compose.core.base

import com.grappim.bankpick.compose.uikit.widgets.states.phone.PhoneInputState
import com.grappim.bankpick.compose.uikit.widgets.states.phone.PhoneInputStateImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface PhoneStateViewModel {
    val phoneState: StateFlow<PhoneInputState>
    fun setPhoneState(phoneInputState: PhoneInputState)
    fun sendPhoneError()
}

class PhoneStateViewModelImpl : PhoneStateViewModel {
    private val _phoneState = MutableStateFlow<PhoneInputState>(PhoneInputStateImpl())
    override val phoneState: StateFlow<PhoneInputState>
        get() = _phoneState.asStateFlow()

    override fun setPhoneState(phoneInputState: PhoneInputState) {
        _phoneState.value = phoneInputState
    }

    override fun sendPhoneError() {
        val oldPhone = phoneState.value
        oldPhone.errorText = phoneState.value.getPhoneErrorText()
        setPhoneState(oldPhone)
    }
}