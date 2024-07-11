package com.example.crypto_currency_app.di

import com.example.crypto_currency_app.common.Constants
import com.example.crypto_currency_app.data.remote.CoinApi
import com.example.crypto_currency_app.data.repository.CoinRepositoryImp
import com.example.crypto_currency_app.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApi(): CoinApi{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URl)
            .addConverterFactory(GsonConverterFactory.create()) //for serealise and desearialise the Json data
            .build()
            .create(CoinApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinApi): CoinRepository{
        return CoinRepositoryImp(api)
    }
}