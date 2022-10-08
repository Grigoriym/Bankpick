package com.grappim.bankpick.compose.uikit.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.grappim.uikit.R
import com.grappim.bankpick.compose.domain.CardData
import com.grappim.bankpick.compose.domain.CardType
import com.grappim.bankpick.compose.ui.theme.BankPickSpunPearl
import com.grappim.bankpick.compose.ui.theme.BankPickTheme

@Composable
fun BankPickCardWidget(
    modifier: Modifier = Modifier,
    cardData: CardData
) {
    Box(
        modifier = modifier
            .size(
                width = 335.dp,
                height = 200.dp
            )
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_card),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
        )

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val (cardNumber,
                fullName,
                expiryDate,
                expiryDateValue,
                cvv,
                cvvValue,
                cardType) = createRefs()

            Text(
                text = cardData.cardNumber,
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier
                    .constrainAs(cardNumber) {
                        bottom.linkTo(
                            anchor = fullName.top,
                            margin = 12.dp
                        )
                        start.linkTo(
                            anchor = fullName.start
                        )
                    }
            )

            Text(
                text = cardData.cardName,
                fontSize = 13.sp,
                color = Color.White,
                modifier = Modifier
                    .constrainAs(fullName) {
                        bottom.linkTo(
                            anchor = expiryDate.top,
                            margin = 16.dp
                        )
                        start.linkTo(
                            anchor = expiryDate.start
                        )
                    }
            )

            Text(
                text = stringResource(id = R.string.expiry_date),
                fontSize = 9.sp,
                color = BankPickSpunPearl,
                modifier = Modifier
                    .constrainAs(expiryDate) {
                        bottom.linkTo(
                            anchor = expiryDateValue.top,
                            margin = 4.dp
                        )
                        start.linkTo(
                            anchor = expiryDateValue.start
                        )
                    }
            )
            Text(
                text = cardData.expiryDate,
                fontSize = 13.sp,
                color = Color.White,
                modifier = Modifier
                    .constrainAs(expiryDateValue) {
                        bottom.linkTo(
                            anchor = parent.bottom,
                            margin = 22.dp
                        )
                        start.linkTo(
                            anchor = parent.start,
                            margin = 20.dp
                        )
                    }
            )

            Text(
                text = stringResource(id = R.string.cvv),
                fontSize = 9.sp,
                color = BankPickSpunPearl,
                modifier = Modifier
                    .constrainAs(cvv) {
                        top.linkTo(
                            anchor = expiryDate.top,
                        )
                        start.linkTo(
                            anchor = expiryDate.end,
                            margin = 30.dp
                        )
                    }
            )
            Text(
                text = cardData.cvv,
                fontSize = 13.sp,
                color = Color.White,
                modifier = Modifier
                    .constrainAs(cvvValue) {
                        top.linkTo(
                            anchor = cvv.bottom,
                            margin = 4.dp
                        )
                        start.linkTo(
                            anchor = cvv.start
                        )
                    }
            )

            Column(
                modifier = Modifier
                    .constrainAs(cardType) {
                        end.linkTo(
                            anchor = parent.end,
                            margin = 16.dp
                        )
                        bottom.linkTo(expiryDateValue.bottom)
                    },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val image = when (cardData.cardType) {
                    CardType.VISA -> {
                        painterResource(id = R.drawable.ic_mastercard)
                    }
                    CardType.MASTERCARD -> {
                        painterResource(id = R.drawable.ic_mastercard)
                    }
                }
                Image(
                    painter = image,
                    contentDescription = ""
                )
                Text(
                    text = cardData.cardType.title,
                    fontSize = 13.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
@Preview(
    showBackground = true
)
private fun BankPickCardWidgetPreview() {
    BankPickTheme {
        BankPickCardWidget(
            cardData = CardData.empty()
        )
    }
}