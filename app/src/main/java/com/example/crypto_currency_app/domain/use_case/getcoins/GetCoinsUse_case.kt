package com.example.crypto_currency_app.domain.use_case.getcoins

import com.example.crypto_currency_app.common.Resource
import com.example.crypto_currency_app.data.remote.dto.toCoin
import com.example.crypto_currency_app.domain.model.Coin
import com.example.crypto_currency_app.domain.repository.CoinRepository
import kotlinx.coroutines.flow.flow
import okio.IOException
import java.net.HttpRetryException
import java.util.concurrent.Flow
import javax.inject.Inject

class GetCoinsUse_case @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke() = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success(coins))
        } catch (e: HttpRetryException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected Error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Please check your internet Connection"))
        }
    }

}