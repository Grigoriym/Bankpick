package com.grappim.bankpick.compose.ui.screens.home.settings

sealed interface GeneralSettingsItem : SettingsItem {

    class Language : GeneralSettingsItem {
        override val name: String = "Language"
    }

    class MyProfile : GeneralSettingsItem {
        override val name: String = "My Profile"
    }

    class ContactUs : GeneralSettingsItem {
        override val name: String = "Contact Us"
    }
}

val generalSettingsItems = listOf(
    GeneralSettingsItem.Language(),
    GeneralSettingsItem.MyProfile(),
    GeneralSettingsItem.ContactUs()
)