package com.example.crypto_currency_app.presentation.coin_detail

import com.example.crypto_currency_app.domain.model.Coin
import com.example.crypto_currency_app.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coins: CoinDetail ?=null,
    val error: String = "",
)