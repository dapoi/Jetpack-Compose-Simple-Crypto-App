package com.project.coinq.presentation.list.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.coinq.model.Coin
import com.project.coinq.presentation.theme.ui.CoinQTheme
import com.project.coinq.presentation.theme.ColorPrimary

@Composable
fun CoinListItem(
    coin: Coin,
    onItemClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(coin.id) }
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onItemClick(coin.id)
                },
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = coin.name,
                color = ColorPrimary,
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Text(
                text = coin.desc,
                color = Color.White,
                style = MaterialTheme.typography.subtitle2,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
        }
    }
}

@Preview
@Composable
fun CoinListItemPreview() {
    CoinQTheme {
        CoinListItem(
            Coin(
                id = 1,
                name = "Bitcoin",
                desc = "Bitcoin is a cryptocurrency",
                tags = listOf("BTC", "BTC"),
            ),
            onItemClick = {}
        )
    }
}