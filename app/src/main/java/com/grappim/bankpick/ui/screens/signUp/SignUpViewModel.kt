package com.grappim.bankpick.ui.screens.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grappim.bankpick.data.RemoteRepository
import com.grappim.bankpick.domain.auth.SignUpData
import com.grappim.bankpick.lce.Try
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val remoteRepository: RemoteRepository
) : ViewModel() {

    private val _signUpResult = MutableSharedFlow<SignUpResult>()
    val signUpResult: SharedFlow<SignUpResult>
        get() = _signUpResult.asSharedFlow()

    fun signUp(
        fullName: String,
        phoneNumber: String,
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            if (email.isEmpty() ||
                phoneNumber.isEmpty() ||
                fullName.isEmpty() ||
                password.isEmpty()
            ) {
                _signUpResult.emit(SignUpResult.Error)
            } else {
                val result = remoteRepository.signUp(
                    SignUpData(
                        fullName = fullName,
                        email = email,
                        password = password,
                        phoneNumber = phoneNumber
                    )
                )
                when(result){
                    is Try.Success -> {
                        _signUpResult.emit(SignUpResult.Success)
                    }
                    is Try.Error -> {
                        _signUpResult.emit(SignUpResult.Error)
                    }
                }
            }
        }
    }
}