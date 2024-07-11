package com.example.crypto_currency_app.presentation

import android.hardware.biometrics.BiometricManager.Strings

sealed class Screen (val route: String) {
    object CoinListScreen : Screen("list_screen")
    object CoinDetailScreen : Screen("Detail_screen")
}