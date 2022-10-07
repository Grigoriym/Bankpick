package com.grappim.bankpick.compose.core

sealed interface RootNavDestinations {
    val route: String

    object OnBoardingDestination : RootNavDestinations {
        override val route: String = "on_boarding_destination"
    }

    object SignInDestination : RootNavDestinations {
        override val route: String = "sign_in_destination"
    }

    object SignUpDestination : RootNavDestinations {
        override val route: String = "sign_up_destination"
    }

    object RootBankPickDestination : RootNavDestinations {
        override val route: String = "root_bank_pick_destination"
    }

    object ProfileDestination : RootNavDestinations {
        override val route: String = "root_profile_destination"
    }

    object CardsDestination : RootNavDestinations {
        override val route: String = "root_cards_destination"
    }
}