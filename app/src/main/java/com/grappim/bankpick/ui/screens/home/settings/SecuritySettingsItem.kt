package com.grappim.bankpick.ui.screens.home.settings

sealed interface SecuritySettingsItem : SettingsItem {

    class ChangePassword : GeneralSettingsItem {
        override val name: String = "Change Password"
    }

    class PrivacyPolicy : GeneralSettingsItem {
        override val name: String = "Privacy Policy"
    }
}

val securitySettingsItems = listOf(
    SecuritySettingsItem.ChangePassword(),
    SecuritySettingsItem.PrivacyPolicy()
)