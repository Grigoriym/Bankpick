package com.grappim.bankpick.compose.ui.screens.home.settings.profile

import com.grappim.bankpick.compose.R

sealed interface ProfileItem {
    val name: String
    val icon: Int

    class PersonalInformation : ProfileItem {
        override val name: String = "Personal Information"
        override val icon: Int = R.drawable.ic_user
    }

    class PaymentPreferences : ProfileItem {
        override val name: String = "Payment Preferences"
        override val icon: Int = R.drawable.ic_payments
    }
    class BanksAndCards : ProfileItem {
        override val name: String = "Banks and Cards"
        override val icon: Int = R.drawable.ic_payments
    }
    class Notifications : ProfileItem {
        override val name: String = "Notifications"
        override val icon: Int = R.drawable.ic_payments
    }
    class MessageCenter : ProfileItem {
        override val name: String = "Message Center"
        override val icon: Int = R.drawable.ic_payments
    }
    class Address : ProfileItem {
        override val name: String = "Address"
        override val icon: Int = R.drawable.ic_payments
    }
}

val profileItems = listOf<ProfileItem>(
    ProfileItem.PersonalInformation(),
    ProfileItem.PaymentPreferences(),
    ProfileItem.BanksAndCards(),
    ProfileItem.Notifications(),
    ProfileItem.MessageCenter(),
    ProfileItem.Address()
)


