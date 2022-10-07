package com.grappim.bankpick.compose.uikit.widgets.states.email

import android.util.Patterns
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue
import com.grappim.bankpick.compose.R
import com.grappim.bankpick.compose.core.NativeText

data class EmailInputStateImpl(
    val initialEmailValue: String = "",
    val initialErrorText: NativeText? = null
) : EmailInputState {

    private var _email by mutableStateOf(initialEmailValue)
    private var _errorText: NativeText? by mutableStateOf(initialErrorText)

    override var errorText: NativeText?
        get() = _errorText
        set(value) {
            _errorText = value
        }

    override var email: String
        get() = _email
        set(value) {
            _errorText = null
            _email = value
        }

    override val isValid by derivedStateOf { isValidEmail(_email) }

    override val isValidEmailPattern: Boolean by derivedStateOf {
        Patterns.EMAIL_ADDRESS.matcher(_email).matches()
    }

    private fun isValidEmail(email: String): Boolean =
        email.isNotEmpty() && isValidEmailPattern

    override fun getEmailErrorText(): NativeText? =
        when {
            _email.isEmpty() -> {
                NativeText.Resource(R.string.auth_email_empty)
            }
            isValidEmailPattern.not() -> {
                NativeText.Resource(R.string.auth_email_validity)
            }
            else -> {
                null
            }
        }

    companion object {
        val Empty = EmailInputStateImpl()

        val Saver = Saver<EmailInputStateImpl, List<Any?>>(
            save = {
                listOf(it._email, it._errorText)
            },
            restore = {
                EmailInputStateImpl(
                    initialEmailValue = it.first() as String,
                    initialErrorText = it[1] as? NativeText
                )
            }
        )
    }
}