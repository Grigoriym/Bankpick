package com.grappim.bankpick.compose.uikit.widgets.states.name

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.grappim.uikit.R
import com.grappim.bankpick.compose.core.NativeText

class NameInputStateImpl(
    initialName: String = "",
    initialErrorText: NativeText? = null
) : NameInputState {

    private var _name by mutableStateOf(initialName)
    private var _errorText: NativeText? by mutableStateOf(initialErrorText)

    override var name: String
        get() = _name
        set(value) {
            _name = value
        }
    override var errorText: NativeText?
        get() = _errorText
        set(value) {
            _errorText = value
        }
    override val isValid: Boolean by derivedStateOf {
        _name.isNotEmpty()
    }

    override fun getNameErrorText(): NativeText? =
        when {
            _name.isEmpty() -> {
                NativeText.Resource(R.string.name_empty)
            }
            else -> {
                null
            }
        }

    companion object {
        val Empty = NameInputStateImpl()
    }
}