package com.example.mybank.db

import com.example.mybank.R
import java.io.Serializable

class StartViewData(val image: Int, val startText: String, val check: Boolean) : Serializable {

    companion object {
        val startList = mutableListOf(
            StartViewData(R.drawable.money, "Привет", false),
            StartViewData(R.drawable.many_money, "Всякое там бла-бла-бла про вклады и проценты", false),
            StartViewData(R.drawable.money_percent, "Наш банк круче всех и вообще все остальные банки полная фигня!", true)
        )
    }
}