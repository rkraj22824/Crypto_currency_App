package com.example.crypto_currency_app.domain.use_case.getcoin

import com.example.crypto_currency_app.common.Resource
import com.example.crypto_currency_app.data.remote.dto.toCoin
import com.example.crypto_currency_app.data.remote.dto.toCoinDetail
import com.example.crypto_currency_app.domain.repository.CoinRepository
import kotlinx.coroutines.flow.flow
import okio.IOException
import java.net.HttpRetryException
import javax.inject.Inject


class GetCoinUse_case @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String) = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoinsById(coinId).toCoinDetail()
            emit(Resource.Success(coin))
        } catch (e: HttpRetryException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected Error occured"))
        } catch (e: IOException) {
            emit(Resource.Error("Please check your internet Connection"))
        }
    }

}