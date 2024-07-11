package com.example.crypto_currency_app.domain.model

import android.hardware.biometrics.BiometricManager.Strings

data class Coin(
    val id: String,
    val is_active: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
)
