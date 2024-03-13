package com.example.dibuild.ui.history

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class HistoryViewModel {
    private val _uiState = MutableStateFlow(HistoryUiState(emptyList<String>()))
    val uiState: StateFlow<HistoryUiState> = _uiState.asStateFlow()

    fun updateHistory(newHistory: List<String>){
        _uiState.value = uiState.value.copy(history = newHistory)
    }
}

