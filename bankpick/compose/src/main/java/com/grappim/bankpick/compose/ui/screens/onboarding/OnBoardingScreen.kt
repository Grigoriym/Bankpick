package com.grappim.bankpick.compose.ui.screens.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grappim.bankpick.common.ui.onBoarding.OnBoardingPages
import com.grappim.bankpick.compose.ui.theme.BankPickTheme
import com.grappim.bankpick.compose.uikit.widgets.BankPickButton
import com.grappim.uikit.R
import kotlinx.coroutines.launch

@Composable
fun OnBoardingScreen(
    onLastScreenNext: () -> Unit
) {
    val pages = listOf(
        OnBoardingPages.First,
        OnBoardingPages.Second,
        OnBoardingPages.Third
    )
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        pageCount = {
            pages.size
        }
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 60.dp)
    ) {
        HorizontalPager(
            modifier = Modifier
                .weight(10f),
            state = pagerState
        ) { position ->
            OnBoardingPagerScreen(
                onBoardingPages = pages[position],
                pagerState = pagerState
            )
        }
        BankPickButton(
            text = stringResource(id = R.string.next),
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .weight(1f),
            onClick = {
                if (pagerState.currentPage == pagerState.pageCount - 1) {
                    onLastScreenNext()
                } else {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            })
    }
}

@Composable
@Preview(
    showBackground = true
)
private fun OnBoardingScreenPreview() {
    BankPickTheme {
        OnBoardingScreen(
            onLastScreenNext = {}
        )
    }
}