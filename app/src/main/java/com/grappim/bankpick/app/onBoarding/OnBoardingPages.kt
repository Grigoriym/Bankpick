package com.grappim.bankpick.app.onBoarding

import androidx.annotation.DrawableRes
import com.grappim.uikit.R

sealed class OnBoardingPages(
    @DrawableRes val image: Int,
    val title: String,
    val description: String
) {
    object First : OnBoardingPages(
        image = R.drawable.ic_onboarding_1,
        title = "Fastest Payment in the world",
        description = "Integrate multiple payment methoods to help you up the process quickly"
    )

    object Second : OnBoardingPages(
        image = R.drawable.ic_onboarding_2,
        title = "The most Secoure Platfrom for Customer",
        description = "Built-in Fingerprint, face recognition and more, keeping you completely safe"
    )

    object Third : OnBoardingPages(
        image = R.drawable.ic_onboarding_3,
        title = "Paying for Everything is Easy and Convenient",
        description = "Built-in Fingerprint, face recognition and more, keeping you completely safe"
    )
}
