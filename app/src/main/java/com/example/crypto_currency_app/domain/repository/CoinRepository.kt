package com.example.crypto_currency_app.domain.repository

import com.example.crypto_currency_app.data.remote.dto.CoinsDetaiDto
import com.example.crypto_currency_app.data.remote.dto.CoinsDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinsDto>

    suspend fun getCoinsById(coinId: String): CoinsDetaiDto

}