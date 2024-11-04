package com.example.mybank

import java.io.Serializable

class StartViewData(val image: Int, val startText: String, val check: Boolean) : Serializable {

    companion object {
        val startList = mutableListOf(
            StartViewData(R.drawable.money, "Привет", false),
            StartViewData(R.drawable.many_money, "Привет", false),
            StartViewData(R.drawable.money_percent, "Привет", true)
        )
    }
}