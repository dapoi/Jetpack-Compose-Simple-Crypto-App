package com.project.coinq.di

import com.project.coinq.data.CoinRepository

object Injection {

    fun provideRepository(): CoinRepository {
        return CoinRepository.getInstance()
    }
}