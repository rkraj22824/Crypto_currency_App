package com.example.crypto_currency_app.data.remote

import com.example.crypto_currency_app.data.remote.dto.CoinsDetaiDto
import com.example.crypto_currency_app.data.remote.dto.CoinsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinApi {

    @GET("/v1/coins")
    suspend fun getCoins():List<CoinsDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinsById(@Path("coinId")coinId:String):CoinsDetaiDto

}