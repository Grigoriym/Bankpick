package com.grappim.bankpick.compose.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.grappim.bankpick.common.ui.root.RootActivityViewModel
import com.grappim.bankpick.compose.core.RootNavDestinations
import com.grappim.bankpick.compose.ui.screens.home.RootHomeBankPickScreen
import com.grappim.bankpick.compose.ui.screens.onboarding.OnBoardingScreen
import com.grappim.bankpick.compose.ui.screens.signIn.SignInScreen
import com.grappim.bankpick.compose.ui.screens.signUp.SignUpScreen
import com.grappim.bankpick.compose.ui.theme.BankPickTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BankPickActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            BankPickTheme {
                BankPickMainScreen()
            }
        }
    }

    @Composable
    private fun BankPickMainScreen(
        viewModel: RootActivityViewModel = hiltViewModel()
    ) {
        val navController = rememberNavController()
        val showOnBoarding = viewModel.showOnBoarding

        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val startDestination = if (showOnBoarding) {
                RootNavDestinations.OnBoardingDestination.route
            } else {
                RootNavDestinations.SignInDestination.route
            }
            NavHost(
                navController = navController,
                startDestination = startDestination
            ) {
                composable(RootNavDestinations.OnBoardingDestination.route) {
                    OnBoardingScreen(
                        onLastScreenNext = {
                            viewModel.setShowOnBoarding(false)
                            navController.navigate(RootNavDestinations.SignInDestination.route)
                        }
                    )
                }
                composable(RootNavDestinations.SignInDestination.route) {
                    SignInScreen(
                        onBackClick = {
                            navController.popBackStack()
                        },
                        signInSuccess = {
                            navController.navigate(RootNavDestinations.RootBankPickDestination.route)
                        },
                        onSignUpClick = {
                            navController.navigate(RootNavDestinations.SignUpDestination.route)
                        }
                    )
                }
                composable(RootNavDestinations.SignUpDestination.route) {
                    SignUpScreen(
                        onBackClick = {
                            navController.popBackStack()
                        },
                        onSignInClick = {
                            navController.popBackStack()
                        },
                        signUpSuccess = {
                            navController.popBackStack()
                        })
                }
                composable(RootNavDestinations.RootBankPickDestination.route) {
                    RootHomeBankPickScreen()
                }
            }
        }
    }
}
