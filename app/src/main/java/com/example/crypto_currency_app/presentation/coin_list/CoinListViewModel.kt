package com.example.crypto_currency_app.presentation.coin_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto_currency_app.common.Resource
import com.example.crypto_currency_app.domain.use_case.getcoin.GetCoinUse_case
import com.example.crypto_currency_app.domain.use_case.getcoins.GetCoinsUse_case
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getcoinsUsecase: GetCoinsUse_case
) : ViewModel() {
    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        getcoinsUsecase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = CoinListState(true)
                }

                is Resource.Success -> {
                    _state.value = CoinListState(
                        coins = result.data ?: emptyList()
                    )
                }

                is Resource.Error -> {
                    _state.value = CoinListState(
                        error = result.message ?: "Unexpected Error occured"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}