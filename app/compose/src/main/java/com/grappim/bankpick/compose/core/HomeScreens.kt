package com.grappim.bankpick.compose.core

import com.grappim.bankpick.compose.R

sealed interface HomeBankPickDestination {
    val route: String
    val icon: Int
    val title: String

    object HomeDestination : HomeBankPickDestination {
        override val route: String = "home_destination"
        override val icon: Int = R.drawable.ic_home
        override val title: String = "Home"
    }

    object MyCardsDestination : HomeBankPickDestination {
        override val route: String = "my_cards_destination"
        override val icon: Int = R.drawable.ic_my_cards
        override val title: String = "My Cards"
    }

    object StatisticsDestination : HomeBankPickDestination {
        override val route: String = "statistics_destination"
        override val icon: Int = R.drawable.ic_statistics
        override val title: String = "Statistics"
    }

    object SettingsDestination : HomeBankPickDestination {
        override val route: String = "settings_destination"
        override val icon: Int = R.drawable.ic_settings
        override val title: String = "Settings"
    }

    object TransactionHistoryDestination :
        HomeBankPickDestination {
        override val route: String = "transaction_history_destination"
        override val icon: Int = R.drawable.ic_settings
        override val title: String = "Transaction History"
    }
}