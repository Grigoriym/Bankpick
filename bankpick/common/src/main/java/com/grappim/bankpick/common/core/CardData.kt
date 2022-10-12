package com.grappim.bankpick.common.core

data class CardData(
    val cardNumber: String,
    val cardName: String,
    val expiryDate: String,
    val cvv: String,
    val cardType: CardType
) {
    companion object {
        fun empty() = CardData(
            cardNumber = "4555 6633 6555 5555",
            cardName = "Gregory ammm",
            expiryDate = "12/26",
            cvv = "123",
            cardType = CardType.MASTERCARD
        )
    }
}
