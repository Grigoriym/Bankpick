package com.grappim.bankpick.compose.ui.screens.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.grappim.bankpick.compose.ui.theme.BankPickTheme
import com.grappim.bankpick.compose.ui.theme.DefaultHorizontalPadding

@Composable
fun OnBoardingPagerScreen(
    onBoardingPages: OnBoardingPages,
    pagerState: PagerState
) {
    Column(
        modifier = Modifier
            .padding(horizontal = DefaultHorizontalPadding)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .requiredSize(300.dp)
        ) {
            Image(
                modifier = Modifier,
                painter = painterResource(id = onBoardingPages.image),
                contentDescription = "Pager Image"
            )
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 40.dp)
        )
        Text(
            text = onBoardingPages.title,
            modifier = Modifier
                .padding(top = 36.dp)
                .padding(
                    horizontal = 20.dp
                ),
            fontSize = 26.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = onBoardingPages.description,
            modifier = Modifier
                .padding(top = 12.dp)
                .padding(
                    horizontal = 20.dp
                ),
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
@Preview(
    showBackground = true
)
private fun OnBoardingPagerScreenPreview() {
    BankPickTheme {
        OnBoardingPagerScreen(
            onBoardingPages = OnBoardingPages.First,
            pagerState = rememberPagerState()
        )
    }
}

@Composable
@Preview(
    showBackground = true
)
private fun OnBoardingPagerScreenPreview2() {
    BankPickTheme {
        OnBoardingPagerScreen(
            onBoardingPages = OnBoardingPages.Second,
            pagerState = rememberPagerState()
        )
    }
}