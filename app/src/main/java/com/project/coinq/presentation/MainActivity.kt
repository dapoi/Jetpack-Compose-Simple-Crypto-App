package com.project.coinq.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.project.coinq.presentation.detail.DetailCoinScreen
import com.project.coinq.presentation.list.CoinScreen
import com.project.coinq.presentation.navigation.Screen
import com.project.coinq.presentation.profile.ProfileScreen
import com.project.coinq.presentation.theme.DarkGray
import com.project.coinq.presentation.theme.ui.CoinQTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoinQTheme {
                CoinApp()
            }
        }
    }
}

@Composable
fun CoinApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        modifier = modifier,
        backgroundColor = DarkGray
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.CoinListScreen.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(
                route = Screen.CoinListScreen.route
            ) {
                CoinScreen(
                    navigateToDetail = { coinId ->
                        navController.navigate(Screen.CoinDetailScreenWithId.createRoute(coinId))
                    },
                    navigateToProfile = {
                        navController.navigate(Screen.ProfileScreen.route)
                    }
                )
            }
            composable(
                route = Screen.CoinDetailScreenWithId.route,
                arguments = listOf(navArgument("coinId") {
                    type = NavType.IntType
                })
            ) {
                val id = it.arguments?.getInt("coinId") ?: 0
                DetailCoinScreen(
                    coinId = id,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
            composable(
                route = Screen.ProfileScreen.route
            ) {
                ProfileScreen(
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}