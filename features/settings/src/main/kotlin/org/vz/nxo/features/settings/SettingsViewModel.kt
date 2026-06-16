package org.vz.nxo.features.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.vz.nxo.core.common.SettingsRepository

data class SettingsUiState(
    val aiSortingEnabled: Boolean = true,
    val geminiApiKey: String = "",
    val topAppsCount: Int = 5
)

class SettingsViewModel(
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SettingsUiState())
    val uiState: StateFlow<SettingsUiState> = _uiState.asStateFlow()

    init {
        loadSettings()
    }

    private fun loadSettings() {
        viewModelScope.launch {
            settingsRepository.isAiSortingEnabled.collect { enabled ->
                _uiState.update { it.copy(aiSortingEnabled = enabled) }
            }
        }
        viewModelScope.launch {
            settingsRepository.geminiApiKey.collect { key ->
                _uiState.update { it.copy(geminiApiKey = key) }
            }
        }
        viewModelScope.launch {
            settingsRepository.topAppsCount.collect { count ->
                _uiState.update { it.copy(topAppsCount = count) }
            }
        }
    }

    fun setAiSortingEnabled(enabled: Boolean) {
        viewModelScope.launch {
            settingsRepository.setAiSortingEnabled(enabled)
        }
    }

    fun setGeminiApiKey(key: String) {
        viewModelScope.launch {
            settingsRepository.setGeminiApiKey(key)
        }
    }

    fun setTopAppsCount(count: Int) {
        viewModelScope.launch {
            settingsRepository.setTopAppsCount(count)
        }
    }
}
