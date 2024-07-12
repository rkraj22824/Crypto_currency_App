package com.example.crypto_currency_app.presentation.coin_detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.crypto_currency_app.presentation.Screen
import com.example.crypto_currency_app.presentation.coin_detail.components.CoinTag
import com.example.crypto_currency_app.presentation.coin_detail.components.TeamListItem
import com.example.crypto_currency_app.presentation.coin_list.components.CoinListItem


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize().padding(start = 12.dp, top = 56.dp, end = 8.dp, bottom = 16.dp)) {
       state.coin?.let { coin->
           LazyColumn(
               modifier = Modifier.fillMaxSize(),
               contentPadding = PaddingValues(20.dp)
           ) {
               item {
                   Row (
                       modifier = Modifier.fillMaxWidth(),
                       horizontalArrangement = Arrangement.SpaceBetween
                   ){
                       Text(
                           text ="${coin.rank}. ${coin.name} (${coin.symbol})",
                           modifier = Modifier.weight(8f)
                       )
                       Text(
                            text =if(coin.is_active) "active" else "inactive",
                           color = if(coin.is_active) Color.Cyan else Color.Red,
                           fontStyle = FontStyle.Italic,
                           textAlign = TextAlign.End,
                           modifier = Modifier
                               .align(CenterVertically)
                               .weight(2f)
                       )

                   }
                   Spacer(modifier = Modifier.height(15.dp))
                   
                   Text(text = coin.description,
                       )
                   Spacer(modifier = Modifier.height(15.dp))
                   Text(text = "Tags ")
                   Spacer(modifier = Modifier.height(15.dp))
                   FlowRow(
                       modifier = Modifier.padding(8.dp),
                       horizontalArrangement = Arrangement.spacedBy(10.dp)
                   ) {
                       coin.tags.forEach { tag ->
                           CoinTag(tag)
                       }
                   }
                   Spacer(modifier = Modifier.height(15.dp))
                   Text(
                       text = "Team members ",
                       )
                   Spacer(modifier = Modifier.height(15.dp))
               }
               items(coin.team){ teamMember->
                   TeamListItem(teamMember = teamMember, modifier = Modifier.fillMaxWidth()
                       .padding(10.dp))
               }
           }
       }
        if (state.error.isNotBlank()) {
            Text(
                text = state.error,

                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

    }

}