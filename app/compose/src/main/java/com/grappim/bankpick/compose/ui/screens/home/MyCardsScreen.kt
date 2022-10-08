package com.grappim.bankpick.compose.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalance
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.grappim.uikit.R
import com.grappim.bankpick.compose.domain.CardData
import com.grappim.bankpick.compose.domain.Transaction
import com.grappim.bankpick.compose.ui.theme.BankPickBlackRussian
import com.grappim.bankpick.compose.ui.theme.BankPickNavyBlue
import com.grappim.bankpick.compose.ui.theme.BankPickTheme
import com.grappim.bankpick.compose.ui.theme.BankPickWhiteSmoke
import com.grappim.bankpick.compose.ui.theme.BankPickWhiteSmoke1
import com.grappim.bankpick.compose.ui.theme.DefaultHorizontalPadding
import com.grappim.bankpick.compose.uikit.widgets.BankPickBackButton
import com.grappim.bankpick.compose.uikit.widgets.BankPickCardWidget
import com.grappim.bankpick.compose.uikit.widgets.BankPickIconButton
import com.grappim.bankpick.compose.uikit.widgets.BankPickTopBarContainer
import com.grappim.bankpick.compose.uikit.widgets.TransactionWidget
import com.smarttoolfactory.slider.ColorfulIconSlider
import com.smarttoolfactory.slider.MaterialSliderDefaults
import com.smarttoolfactory.slider.SliderBrushColor
import kotlin.math.roundToInt

@Composable
fun MyCardsScreen(
    onBackPressed: () -> Unit,
    onCardsClick: () -> Unit,
    onTransactionClick: (Transaction) -> Unit
) {
    Scaffold(
        topBar = {
            MyCardsTopBar(
                onBackPressed = onBackPressed,
                onCardsClick = onCardsClick
            )
        }
    ) { paddingValues ->
        MyCardsScreenContent(
            paddingValues = paddingValues,
            onTransactionClick = onTransactionClick
        )
    }
}

@Composable
private fun MyCardsScreenContent(
    paddingValues: PaddingValues,
    onTransactionClick: (Transaction) -> Unit
) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
    ) {
        BankPickCardWidget(
            modifier = Modifier
                .padding(
                    top = 32.dp
                )
                .padding(horizontal = DefaultHorizontalPadding)
                .align(Alignment.CenterHorizontally)
                .weight(1f),
            cardData = CardData.empty()
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(
                    top = 30.dp
                )
        ) {
            items(Transaction.transactionItems()) { item ->
                TransactionWidget(
                    transaction = item,
                    onClick = onTransactionClick
                )
            }
        }

        MonthlySpendingSlider()
    }
}

@Composable
fun ColumnScope.MonthlySpendingSlider() {
    Column(
        modifier = Modifier
            .weight(1f)
            .padding(
                top = 29.dp
            )
            .padding(
                horizontal = 20.dp
            )
            .fillMaxWidth()
    ) {
        val cardBackground = if (isSystemInDarkTheme()) {
            BankPickBlackRussian
        } else {
            BankPickWhiteSmoke1
        }

        Text(
            text = stringResource(id = R.string.monthly_spending_limit),
            fontSize = 18.sp
        )
        Card(
            modifier = Modifier
                .padding(
                    top = 19.dp
                ),
            backgroundColor = cardBackground,
            shape = RoundedCornerShape(18.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(
                        horizontal = 24.dp,
                        vertical = 23.dp
                    )
            ) {
                Column {
                    var labelProgress by remember { mutableStateOf(0f) }
                    var labelOffset by remember { mutableStateOf(Offset.Zero) }
                    var labelWidth by remember { mutableStateOf(0) }

                    val amountColor = if (isSystemInDarkTheme()) {
                        Color.White
                    } else {
                        BankPickBlackRussian
                    }
                    Text(
                        text = "Amount: \$8,545.00",
                        fontSize = 13.sp,
                        color = amountColor,
                    )

                    ColorfulIconSlider(
                        value = labelProgress,
                        onValueChange = { value: Float, offset: Offset ->
                            labelProgress = value
                            labelOffset = offset
                        },
                        trackHeight = 7.dp,
                        valueRange = 0f..10000f,
                        colors = MaterialSliderDefaults.materialColors(
                            activeTrackColor = SliderBrushColor(color = BankPickNavyBlue)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .size(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(16.dp)
                                    .background(BankPickNavyBlue, CircleShape)
                            )
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .background(Color.White, CircleShape)
                            )
                        }
                    }

                    Text(text = "$${labelProgress.roundToInt()}",
                        modifier = Modifier
                            .offset {
                                IntOffset(
                                    labelOffset.x.toInt() - labelWidth / 2,
                                    labelOffset.y.toInt()
                                )
                            }
                            .onSizeChanged {
                                labelWidth = it.width
                            }
                            .shadow(1.dp, shape = RoundedCornerShape(10.dp))
                            .background(
                                BankPickWhiteSmoke,
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(5.dp),
                        color = Color.Black
                    )
                }
            }
        }
    }
}

@Composable
private fun MyCardsTopBar(
    onBackPressed: () -> Unit,
    onCardsClick: () -> Unit,
) {
    BankPickTopBarContainer(
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        BankPickBackButton(
            onClick = onBackPressed
        )
        Text(
            text = stringResource(id = R.string.my_cards),
            fontSize = 18.sp
        )
        BankPickIconButton(
            onClick = onCardsClick,
            imageVector = Icons.Outlined.AccountBalance
        )
    }
}

@Composable
@Preview(
    showBackground = true
)
private fun MyCardsScreenPreview() {
    BankPickTheme {
        MyCardsScreen(
            onBackPressed = {},
            onCardsClick = {},
            onTransactionClick = {}
        )
    }
}