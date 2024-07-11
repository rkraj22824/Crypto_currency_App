package com.example.crypto_currency_app.presentation.coin_detail

import com.example.crypto_currency_app.presentation.coin_list.CoinListState

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.crypto_currency_app.common.Constants
import com.example.crypto_currency_app.common.Resource
import com.example.crypto_currency_app.domain.use_case.getcoin.GetCoinUse_case
import com.example.crypto_currency_app.domain.use_case.getcoins.GetCoinsUse_case
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private  val getcoinUsecase: GetCoinUse_case,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state= mutableStateOf(CoinDetailState())
    val state : State<CoinDetailState> =_state

    init {
       savedStateHandle.get<String>(Constants.COIN_ID)?.let { coinid ->
           getCoin(coinid)
       }
    }
    private  fun getCoin(coinId :String){
        getcoinUsecase(coinId).onEach { result->
            when(result){
                is Resource.Loading ->{
                    _state.value= CoinDetailState(true)
                }
                is Resource.Success ->{
                    _state.value= CoinDetailState(
                        coins = result.data
                    )
                }
                is Resource.Error ->{
                    _state.value = CoinDetailState(
                        error = result.message ?: "Unexpected Error occured"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}