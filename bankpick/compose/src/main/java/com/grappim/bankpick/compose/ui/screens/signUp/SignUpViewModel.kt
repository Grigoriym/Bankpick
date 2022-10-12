package com.grappim.bankpick.compose.ui.screens.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.bankpick.compose.core.base.EmailStateViewModel
import com.grappim.bankpick.compose.core.base.EmailStateViewModelImpl
import com.grappim.bankpick.compose.core.base.LoadingViewModel
import com.grappim.bankpick.compose.core.base.LoadingViewModelImpl
import com.grappim.bankpick.compose.core.base.NameStateViewModel
import com.grappim.bankpick.compose.core.base.NameStateViewModelImpl
import com.grappim.bankpick.compose.core.base.PasswordStateViewModel
import com.grappim.bankpick.compose.core.base.PasswordStateViewModelImpl
import com.grappim.bankpick.compose.core.base.PhoneStateViewModel
import com.grappim.bankpick.compose.core.base.PhoneStateViewModelImpl
import com.grappim.bankpick.compose.core.base.SnackbarStateViewModel
import com.grappim.bankpick.compose.core.base.SnackbarStateViewModelImpl
import com.grappim.bankpick.compose.core.getErrorMessage
import com.grappim.bankpick.data.RemoteRepository
import com.grappim.bankpick.domain.lce.Try
import com.grappim.bankpick.domain.model.auth.SignUpData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository
) : ViewModel(),
    LoadingViewModel by LoadingViewModelImpl(),
    PasswordStateViewModel by PasswordStateViewModelImpl(),
    EmailStateViewModel by EmailStateViewModelImpl(),
    SnackbarStateViewModel by SnackbarStateViewModelImpl(),
    PhoneStateViewModel by PhoneStateViewModelImpl(),
    NameStateViewModel by NameStateViewModelImpl() {

    private val _signUpResult = MutableSharedFlow<SignUpResult>()
    val signUpResult: SharedFlow<SignUpResult>
        get() = _signUpResult.asSharedFlow()

    fun signUp() {
        val email = emailState.value.email
        val phoneNumber = phoneState.value.phone
        val fullName = nameState.value.name
        val password = passwordState.value.password

        viewModelScope.launch {
            if (emailState.value.isValid.not() ||
                phoneState.value.isValid.not() ||
                nameState.value.isValid.not() ||
                passwordState.value.isValid.not()
            ) {
                sendEmailError()
                sendPasswordError()
                sendPhoneError()
                sendNameError()

                _signUpResult.emit(SignUpResult.Error)
            } else {
                setLoading(true)
                val result = remoteRepository.signUp(
                    SignUpData(
                        fullName = fullName,
                        email = email,
                        password = password,
                        phoneNumber = phoneNumber
                    )
                )
                when (result) {
                    is Try.Success -> {
                        _signUpResult.emit(SignUpResult.Success)
                    }
                    is Try.Error -> {
                        _signUpResult.emit(SignUpResult.Error)
                        setSnackbarMessageSuspend(result.result.getErrorMessage())
                    }
                }
                setLoading(false)
            }
        }
    }
}