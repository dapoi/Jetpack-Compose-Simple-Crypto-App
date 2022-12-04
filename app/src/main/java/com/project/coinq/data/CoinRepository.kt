package com.project.coinq.data

import com.project.coinq.model.Coin
import com.project.coinq.model.FakeCoinDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CoinRepository {

    private var coins = mutableListOf<Coin>()

    init {
        FakeCoinDataSource.listCoin.forEach { coin ->
            coins.add(coin)
        }
    }

    fun getCoins(): Flow<List<Coin>> = flowOf(coins)

    fun getCoinById(id: Int): Coin = coins.first { it.id == id }

    companion object {
        @Volatile
        private var instance: CoinRepository? = null

        fun getInstance(): CoinRepository = instance ?: synchronized(this) {
            CoinRepository().apply {
                instance = this
            }
        }
    }
}