package com.grappim.bankpick.compose.core

import android.content.Context
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import com.grappim.uikit.R
import com.grappim.bankpick.compose.domain.model.exception.NetworkException

sealed class NativeText {
    object Empty : NativeText()
    data class Simple(val text: String) : NativeText()
    data class Resource(@StringRes val id: Int) : NativeText()
    data class Plural(@PluralsRes val id: Int, val number: Int, val args: List<Any>) : NativeText()
    data class Arguments(@StringRes val id: Int, val args: List<Any>) : NativeText()
    data class Multi(val text: List<NativeText>) : NativeText()
}

fun NativeText.asString(context: Context): String {
    return when (this) {
        is NativeText.Arguments -> context.getString(id, *args.toTypedArray())
        is NativeText.Multi -> {
            val builder = StringBuilder()
            for (t in text) {
                builder.append(t.asString(context))
            }
            builder.toString()
        }
        is NativeText.Plural -> context.resources.getQuantityString(
            id,
            number,
            *args.toTypedArray()
        )
        is NativeText.Resource -> context.getString(id)
        is NativeText.Simple -> text
        is NativeText.Empty -> ""
    }
}

fun Throwable.getErrorMessage(): NativeText {
    return if (this is NetworkException) {
        when (this.errorCode) {
            NetworkException.ERROR_NO_INTERNET -> {
                NativeText.Resource(R.string.error_no_internet_connection)
            }
            else -> {
                NativeText.Resource(R.string.error_something_has_gone_wrong)
            }
        }
    } else {
        NativeText.Simple(this.message.toString())
    }
}