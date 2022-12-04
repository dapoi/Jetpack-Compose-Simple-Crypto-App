package com.project.coinq.presentation.navigation

sealed class Screen(val route: String) {
    object CoinListScreen : Screen("coin_list_screen")
    object CoinDetailScreenWithId : Screen("coin_detail_screen/{coinId}") {
        fun createRoute(coinId: Int) = "coin_detail_screen/$coinId"
    }
    object ProfileScreen : Screen("profile_screen")
}
