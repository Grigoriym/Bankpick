package com.grappim.bankpick.ui.screens.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.bankpick.R
import com.grappim.bankpick.core.NativeText
import com.grappim.bankpick.data.RemoteRepository
import com.grappim.bankpick.domain.auth.SignInData
import com.grappim.bankpick.lce.Try
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository
) : ViewModel() {

    private val _signInResult = MutableSharedFlow<SignInResult>()
    val signInResult: SharedFlow<SignInResult>
        get() = _signInResult.asSharedFlow()

    fun signIn(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            if (email.isEmpty() || password.isEmpty()) {
                _signInResult.emit(
                    SignInResult.Error(
                        signInFieldsValidationData = SignInFieldsValidationData(
                            emailErrorText = if (email.isEmpty()) {
                                NativeText.Resource(R.string.auth_email_empty)
                            } else {
                                null
                            },
                            passwordErrorText = if (password.isEmpty()) {
                                NativeText.Resource(R.string.auth_password_empty)
                            } else {
                                null
                            }
                        )
                    )
                )
            } else {
                val result = remoteRepository.signIn(
                    SignInData(
                        email = email,
                        password = password
                    )
                )
                when (result) {
                    is Try.Success -> {
                        _signInResult.emit(SignInResult.Success)
                    }
                    is Try.Error -> {
                        _signInResult.emit(
                            SignInResult.Error(
                                generalError = NativeText.Simple(result.result.message ?: "")
                            )
                        )
                    }
                }
            }
        }
    }

}