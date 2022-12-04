package com.project.coinq.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.project.coinq.data.CoinRepository
import com.project.coinq.presentation.detail.DetailCoinViewModel
import com.project.coinq.presentation.list.CoinListViewModel

class ViewModelFactory(
    private val repository: CoinRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(CoinListViewModel::class.java) -> {
                CoinListViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailCoinViewModel::class.java) -> {
                DetailCoinViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}