package com.grappim.bankpick.common.ui.root

import androidx.lifecycle.ViewModel
import com.grappim.bankpick.common.core.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RootActivityViewModel @Inject constructor(
    private val localRepository: LocalRepository
) : ViewModel() {

    val showOnBoarding: Boolean
        get() = localRepository.showOnBoarding

    fun setShowOnBoarding(show: Boolean) {
        localRepository.showOnBoarding = show
    }

}