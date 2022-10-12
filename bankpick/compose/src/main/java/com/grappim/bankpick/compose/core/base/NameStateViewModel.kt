package com.grappim.bankpick.compose.core.base

import com.grappim.bankpick.compose.uikit.widgets.states.name.NameInputState
import com.grappim.bankpick.compose.uikit.widgets.states.name.NameInputStateImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface NameStateViewModel {
    val nameState: StateFlow<NameInputState>
    fun setNameState(nameInputState: NameInputState)
    fun sendNameError()
}

class NameStateViewModelImpl : NameStateViewModel {
    private val _nameState = MutableStateFlow<NameInputState>(NameInputStateImpl())
    override val nameState: StateFlow<NameInputState>
        get() = _nameState.asStateFlow()

    override fun setNameState(nameInputState: NameInputState) {
        _nameState.value = nameInputState
    }

    override fun sendNameError() {
        val oldNameState = nameState.value
        oldNameState.errorText = nameState.value.getNameErrorText()
        setNameState(oldNameState)
    }
}