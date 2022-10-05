package com.grappim.bankpick.ui.screens.home.settings.profile

import androidx.lifecycle.ViewModel
import com.grappim.bankpick.data.LocalRepository
import com.grappim.bankpick.domain.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {

    private val _user = MutableStateFlow(localRepository.user)
    val user: StateFlow<User>
        get() = _user.asStateFlow()

}