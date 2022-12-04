package com.project.coinq.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.coinq.data.CoinRepository
import com.project.coinq.model.Coin
import com.project.coinq.vo.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailCoinViewModel(private val repository: CoinRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UIState<Coin>> = MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<Coin>>
        get() = _uiState

    fun getCoinById(coinId: Int) {
        viewModelScope.launch {
            _uiState.value = UIState.Loading
            _uiState.value = UIState.Success(repository.getCoinById(coinId))
        }
    }
}