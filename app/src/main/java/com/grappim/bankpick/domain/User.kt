package com.grappim.bankpick.domain

data class User(
    val name: String,
    val position: String,
    val cardData: CardData
) {
    companion object {
        fun empty(): User = User(
            name = "name",
            position = "SWE",
            cardData = CardData.empty()
        )
    }
}
