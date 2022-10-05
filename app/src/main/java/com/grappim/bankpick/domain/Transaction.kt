package com.grappim.bankpick.domain

data class Transaction(
    val name: String,
    val icon: String,
    val category: String,
    val sum: String
) {
    companion object {
        fun empty(): Transaction =
            Transaction(name = "transaction", icon = "", category = "cate", sum = "78")

        fun transactionItems(): List<Transaction> {
            val result = mutableListOf<Transaction>()
            repeat(20) {
                result.add(
                    Transaction(
                        name = "Transaction: $it",
                        icon = "",
                        category = "Category: $it",
                        sum = "${it + 120}"
                    )
                )
            }
            return result.toList()
        }
    }
}

