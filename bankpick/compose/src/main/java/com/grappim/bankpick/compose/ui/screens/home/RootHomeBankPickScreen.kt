package com.grappim.bankpick.compose.ui.screens.home

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.grappim.bankpick.compose.core.HomeBankPickDestination
import com.grappim.bankpick.compose.core.RootNavDestinations
import com.grappim.bankpick.compose.ui.screens.cards.AllCardsScreen
import com.grappim.bankpick.compose.ui.screens.home.settings.GeneralSettingsItem
import com.grappim.bankpick.compose.ui.screens.home.settings.SecuritySettingsItem
import com.grappim.bankpick.compose.ui.screens.home.settings.SettingsScreen
import com.grappim.bankpick.compose.ui.screens.home.settings.profile.EditProfileScreen
import com.grappim.bankpick.compose.ui.screens.home.settings.profile.ProfileScreen
import com.grappim.bankpick.compose.ui.theme.BankPickBlackRussian
import com.grappim.bankpick.compose.ui.theme.BankPickManatee
import com.grappim.bankpick.compose.ui.theme.BankPickNavyBlue
import com.grappim.bankpick.compose.ui.theme.BankPickTheme
import com.grappim.bankpick.compose.ui.theme.BankPickWhiteSmoke1
import com.grappim.bankpick.compose.uikit.widgets.icon.BankPickDefaultIcon
import com.grappim.bankpick.domain.model.transaction.Transaction

@Composable
fun RootHomeBankPickScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            val screens: List<HomeBankPickDestination> = listOf(
                HomeBankPickDestination.HomeDestination,
                HomeBankPickDestination.MyCardsDestination,
                HomeBankPickDestination.StatisticsDestination,
                HomeBankPickDestination.SettingsDestination
            )
            val innerScreens = listOf(
                HomeBankPickDestination.TransactionHistoryDestination
            )

            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            val bottomBarDestination = screens.any { it.route == currentDestination?.route } ||
                    innerScreens.any { it.route == currentDestination?.route }

            val contentColor = if (isSystemInDarkTheme()) {
                BankPickBlackRussian
            } else {
                BankPickWhiteSmoke1
            }
            if (bottomBarDestination) {
                BottomNavigation(
                    backgroundColor = contentColor,
                    modifier = Modifier
                        .navigationBarsPadding()
                ) {
                    screens.forEach { screen ->
                        val isSelected = currentDestination?.hierarchy?.any {
                            it.route == screen.route
                        } == true
                        val currentColor = if (isSelected) {
                            BankPickNavyBlue
                        } else {
                            BankPickManatee
                        }

                        BottomNavigationItem(
                            label = {
                                Text(
                                    text = screen.title,
                                    color = currentColor
                                )
                            },
                            icon = {
                                BankPickDefaultIcon(
                                    painter = painterResource(id = screen.icon),
                                    tint = currentColor
                                )
                            },
                            selected = isSelected,
                            alwaysShowLabel = true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id)
                                    launchSingleTop = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { padding ->
        HomeNavGraph(
            navController = navController,
            modifier = Modifier
                .padding(padding)
        )
    }
}

@Composable
fun HomeNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        route = RootNavDestinations.RootBankPickDestination.route,
        startDestination = HomeBankPickDestination.HomeDestination.route,
        modifier = modifier
    ) {
        composable(HomeBankPickDestination.HomeDestination.route) {
            HomeScreen(
                onSeeAllClick = {
                    navController.navigate(HomeBankPickDestination.TransactionHistoryDestination.route)
                },
                onTransactionClick = { transaction: Transaction ->

                }
            )
        }
        composable(HomeBankPickDestination.MyCardsDestination.route) {
            MyCardsScreen(
                onBackPressed = {
                    navController.popBackStack()
                },
                onCardsClick = {
                    navController.navigate(CardsDestination.AllCards.route)
                },
                onTransactionClick = { transaction: Transaction ->

                }
            )
        }
        composable(HomeBankPickDestination.StatisticsDestination.route) {
            StatisticsScreen(
                onBackClicked = {
                    navController.popBackStack()
                },
                onNotificationsClicked = {

                },
                onSeeAllClicked = {
                    navController.navigate(HomeBankPickDestination.TransactionHistoryDestination.route)
                },
                onTransactionClicked = { transaction: Transaction ->

                }
            )
        }
        composable(HomeBankPickDestination.SettingsDestination.route) {
            SettingsScreen(
                onSettingsItemClicked = {
                    when (it) {
                        is GeneralSettingsItem.Language -> {

                        }
                        is GeneralSettingsItem.MyProfile -> {
                            navController.navigate(RootNavDestinations.ProfileDestination.route)
                        }
                        is GeneralSettingsItem.ContactUs -> {

                        }
                        is SecuritySettingsItem.ChangePassword -> {

                        }
                        is SecuritySettingsItem.PrivacyPolicy -> {

                        }
                    }
                }
            )
        }
        composable(HomeBankPickDestination.TransactionHistoryDestination.route) {
            TransactionHistoryScreen(
                onBackPressed = {
                    navController.popBackStack()
                },
                onTransactionClick = { transaction ->

                }
            )
        }
        settingsItemsNavGraph(navController)
        cardsNavGraph(navController)
    }
}

private fun NavGraphBuilder.cardsNavGraph(navController: NavHostController) {
    navigation(
        route = RootNavDestinations.CardsDestination.route,
        startDestination = CardsDestination.AllCards.route
    ) {
        composable(CardsDestination.AllCards.route) {
            AllCardsScreen(
                onBackPressed = {
                    navController.popBackStack()
                }
            )
        }
        composable(CardsDestination.AddCard.route) {

        }
    }
}

private fun NavGraphBuilder.settingsItemsNavGraph(navController: NavHostController) {
    navigation(
        route = RootNavDestinations.ProfileDestination.route,
        startDestination = ProfileDestination.Profile.route
    ) {
        composable(ProfileDestination.Profile.route) {
            ProfileScreen(
                onBackClicked = {
                    navController.popBackStack()
                },
                onEditProfileClicked = {
                    navController.navigate(ProfileDestination.EditProfile.route)
                }
            )
        }
        composable(ProfileDestination.EditProfile.route) {
            EditProfileScreen(
                onBackClicked = {
                    navController.popBackStack()
                }
            )
        }
    }
}

@Composable
@Preview
private fun RootHomeBankPickScreenPreview() {
    BankPickTheme {
        RootHomeBankPickScreen()
    }
}

sealed interface ProfileDestination {
    val route: String

    object Profile : ProfileDestination {
        override val route: String = "profile_destination"
    }

    object EditProfile : ProfileDestination {
        override val route: String = "edit_profile_destination"
    }
}

sealed interface CardsDestination {
    val route: String

    object AllCards : CardsDestination {
        override val route: String = "all_cards_destination"
    }

    object AddCard : CardsDestination {
        override val route: String = "add_card_destination"
    }
}