package com.grappim.bankpick.compose.core.base

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

interface LoadingViewModel {
    val loading: StateFlow<Boolean>
    fun setLoading(value: Boolean)
}

class LoadingViewModelImpl : LoadingViewModel {
    private val _loading = MutableStateFlow(false)
    override val loading: StateFlow<Boolean>
        get() = _loading.asStateFlow()

    override fun setLoading(value: Boolean) {
        _loading.value = value
    }
}