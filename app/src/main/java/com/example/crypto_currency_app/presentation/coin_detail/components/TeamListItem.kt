package com.example.crypto_currency_app.presentation.coin_detail.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.crypto_currency_app.data.remote.dto.TeamMember

@Composable
fun TeamListItem(
    teamMember: TeamMember,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = teamMember.name
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = teamMember.position,
            fontStyle = FontStyle.Italic
        )
    }
}