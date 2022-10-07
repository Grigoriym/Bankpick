package com.grappim.bankpick.compose.core.base

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

interface LoadingViewModel {
    val loading: State<Boolean>
    fun setLoading(value: Boolean)
}

class LoadingViewModelImpl : LoadingViewModel {
    private val _loading = mutableStateOf(false)
    override val loading: State<Boolean>
        get() = _loading

    override fun setLoading(value: Boolean) {
        _loading.value = value
    }
}