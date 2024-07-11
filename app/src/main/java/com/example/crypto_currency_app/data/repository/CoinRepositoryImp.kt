package com.example.crypto_currency_app.data.repository

import com.example.crypto_currency_app.data.remote.CoinApi
import com.example.crypto_currency_app.data.remote.dto.CoinsDetaiDto
import com.example.crypto_currency_app.data.remote.dto.CoinsDto
import com.example.crypto_currency_app.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImp @Inject constructor(
    private val api: CoinApi
) : CoinRepository {
    override suspend fun getCoins(): List<CoinsDto> {
        return api.getCoins()
    }

    override suspend fun getCoinsById(coinId: String): CoinsDetaiDto {
        return api.getCoinsById(coinId)
    }
}