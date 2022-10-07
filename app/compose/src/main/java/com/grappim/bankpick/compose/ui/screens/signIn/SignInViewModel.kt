package com.grappim.bankpick.compose.ui.screens.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.bankpick.compose.core.base.EmailStateViewModel
import com.grappim.bankpick.compose.core.base.EmailStateViewModelImpl
import com.grappim.bankpick.compose.core.base.LoadingViewModel
import com.grappim.bankpick.compose.core.base.LoadingViewModelImpl
import com.grappim.bankpick.compose.core.base.PasswordStateViewModel
import com.grappim.bankpick.compose.core.base.PasswordStateViewModelImpl
import com.grappim.bankpick.compose.core.base.SnackbarStateViewModel
import com.grappim.bankpick.compose.core.base.SnackbarStateViewModelImpl
import com.grappim.bankpick.compose.core.getErrorMessage
import com.grappim.bankpick.compose.data.RemoteRepository
import com.grappim.bankpick.compose.domain.auth.SignInData
import com.grappim.bankpick.compose.lce.Try
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository
) : ViewModel(),
    LoadingViewModel by LoadingViewModelImpl(),
    PasswordStateViewModel by PasswordStateViewModelImpl(),
    EmailStateViewModel by EmailStateViewModelImpl(),
    SnackbarStateViewModel by SnackbarStateViewModelImpl() {

    private val _signInResult = MutableSharedFlow<SignInResult>()
    val signInResult: SharedFlow<SignInResult>
        get() = _signInResult.asSharedFlow()

    fun signIn() {
        val email = emailState.value.email
        val password = passwordState.value.password

        viewModelScope.launch {
            if (emailState.value.isValid.not() || passwordState.value.isValid.not()) {
                sendEmailError()
                sendPasswordError()

                _signInResult.emit(SignInResult.Error)
            } else {
                setLoading(true)
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
                        _signInResult.emit(SignInResult.Error)
                        setSnackbarMessageSuspend(result.result.getErrorMessage())
                    }
                }
                setLoading(false)
            }
        }
    }

}