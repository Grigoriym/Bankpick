package com.grappim.bankpick.data

import android.content.Context
import com.grappim.bankpick.domain.CardData
import com.grappim.bankpick.domain.User
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {

    companion object {
        private const val ARG_KEY_SHOW_ON_BOARDING = "arg.key.show.on.boarding"
    }

    private val sharedPrefs = context.getSharedPreferences(
        "BankPick_prefs",
        Context.MODE_PRIVATE
    )

    private val edit = sharedPrefs.edit()

    var showOnBoarding: Boolean = true
        get() = sharedPrefs.getBoolean(ARG_KEY_SHOW_ON_BOARDING, true)
        set(value) {
            field = value
            edit.putBoolean(ARG_KEY_SHOW_ON_BOARDING, value).apply()
        }

    var user: User = User(
        name = "Grigoriy Mik",
        position = "SWE",
        cardData = CardData.empty()
    )
}