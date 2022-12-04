package com.project.coinq.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.coinq.data.CoinRepository
import com.project.coinq.model.Coin
import com.project.coinq.vo.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CoinListViewModel(private val repository: CoinRepository) : ViewModel() {

    private val _uiState: MutableStateFlow<UIState<List<Coin>>> = MutableStateFlow(UIState.Loading)
    val uiState: StateFlow<UIState<List<Coin>>>
        get() = _uiState

    fun getAllCoins() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getCoins()
                .catch {
                    _uiState.value = UIState.Error(it.message.toString())
                }
                .collect {
                    _uiState.value = UIState.Success(it)
                }
        }
    }
}