package com.project.coinq.presentation.list

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.project.coinq.R
import com.project.coinq.di.Injection
import com.project.coinq.model.Coin
import com.project.coinq.model.FakeCoinDataSource
import com.project.coinq.presentation.ViewModelFactory
import com.project.coinq.presentation.list.component.CoinListItem
import com.project.coinq.presentation.theme.TextWhite
import com.project.coinq.presentation.theme.ui.CoinQTheme
import com.project.coinq.vo.UIState

@Composable
fun CoinScreen(
    viewModel: CoinListViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateToDetail: (Int) -> Unit,
    navigateToProfile: () -> Unit
) {
    val localContext = LocalContext.current
    viewModel.uiState.collectAsState(initial = UIState.Loading).value.let { uiState ->
        when (uiState) {
            is UIState.Loading -> {
                viewModel.getAllCoins()
            }
            is UIState.Success -> {
                CoinContent(
                    coin = uiState.data,
                    navigateToDetail = navigateToDetail,
                    navigateToProfile = navigateToProfile
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
fun AppBar(
    modifier: Modifier = Modifier,
    navigateToProfile: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            color = TextWhite,
            fontSize = 20.sp,
            style = MaterialTheme.typography.subtitle2.copy(
                fontWeight = FontWeight.ExtraBold
            )
        )
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Account",
            modifier = Modifier
                .clickable { navigateToProfile() }
                .size(32.dp),
            colorResource(id = R.color.white)
        )
    }
}

@Composable
fun CoinContent(
    modifier: Modifier = Modifier,
    coin: List<Coin>,
    navigateToDetail: (Int) -> Unit,
    navigateToProfile: () -> Unit
) {
    Column {
        AppBar(navigateToProfile = navigateToProfile)
        LazyColumn(
            modifier = modifier.fillMaxSize(),
        ) {
            items(coin) { data ->
                CoinListItem(
                    coin = data,
                    onItemClick = navigateToDetail
                )
            }
        }
    }
}

@Preview
@Composable
fun CoinContentPreview() {
    CoinQTheme(darkTheme = false) {
        CoinContent(
            coin = FakeCoinDataSource.listCoin,
            navigateToDetail = {},
            navigateToProfile = {}
        )
    }
}