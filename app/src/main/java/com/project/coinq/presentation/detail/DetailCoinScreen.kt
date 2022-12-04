package com.project.coinq.presentation.detail

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.flowlayout.FlowRow
import com.project.coinq.R
import com.project.coinq.di.Injection
import com.project.coinq.model.Coin
import com.project.coinq.presentation.ViewModelFactory
import com.project.coinq.presentation.theme.ColorPrimary
import com.project.coinq.vo.UIState

@Composable
fun DetailCoinScreen(
    coinId: Int,
    viewModel: DetailCoinViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
) {
    val localContext = LocalContext.current
    viewModel.uiState.collectAsState(initial = UIState.Loading).value.let { uiState ->
        when (uiState) {
            is UIState.Loading -> {
                viewModel.getCoinById(coinId)
            }
            is UIState.Success -> {
                DetailCoinContent(
                    coin = uiState.data,
                    navigateBack = navigateBack
                )
            }
            is UIState.Error -> {
                Toast.makeText(
                    localContext,
                    "Error",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}

@Composable
fun DetailCoinContent(
    modifier: Modifier = Modifier,
    coin: Coin,
    navigateBack: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier
                    .clickable { navigateBack() },
                colorResource(id = R.color.white)
            )
        }
        Text(
            text = coin.name,
            color = Color.White,
            style = MaterialTheme.typography.subtitle1.copy(
                fontWeight = FontWeight.ExtraBold
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = coin.desc, color = Color.White)
        Spacer(modifier = Modifier.height(16.dp))
        FlowRow(
            mainAxisSpacing = 8.dp,
            crossAxisSpacing = 8.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            for (coinTags in coin.tags) {
                CoinTag(tag = coinTags)
            }
        }
    }
}

@Composable
fun CoinTag(
    tag: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = ColorPrimary,
                shape = RoundedCornerShape(100.dp)
            )
            .padding(10.dp),
        Alignment.Center
    ) {
        Text(
            text = tag,
            color = ColorPrimary,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.body2
        )
    }
}

@Preview
@Composable
fun DetailCoinContentPreview() {
    DetailCoinContent(
        coin = Coin(
            id = 1,
            name = "Bitcoin",
            desc = "Bitcoin is a cryptocurrency, a form of electronic cash. It is a decentralized digital currency without a central bank or single administrator that can be sent from user to user on the peer-to-peer bitcoin network without the need for intermediaries.",
            tags = listOf("Bitcoin", "BTC", "Crypto")
        ),
        navigateBack = {}
    )
}