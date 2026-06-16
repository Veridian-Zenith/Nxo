package org.vz.nxo.features.launcher

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.vz.nxo.core.common.AppManager
import org.vz.nxo.core.common.AppInfo
import org.vz.nxo.core.common.Resource
import org.vz.nxo.core.common.SettingsRepository
import org.vz.nxo.features.aiengine.AiRepository
import org.vz.nxo.features.aiengine.db.AppEntity

import org.vz.nxo.core.common.PermissionManager

data class LauncherUiState(
    val apps: List<AppEntity> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null,
    val isAiEnabled: Boolean = true,
    val isPermissionGranted: Boolean = true
)

class LauncherViewModel(
    private val aiRepository: AiRepository,
    private val appManager: AppManager,
    private val settingsRepository: SettingsRepository,
    private val permissionManager: PermissionManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(LauncherUiState())
    val uiState: StateFlow<LauncherUiState> = _uiState.asStateFlow()

    init {
        checkPermission()
        observeSettingsAndApps()
        syncRankings()
    }

    private fun checkPermission() {
        val granted = permissionManager.hasUsageStatsPermission()
        _uiState.update { it.copy(isPermissionGranted = granted) }
    }

    fun requestUsagePermission() {
        permissionManager.openUsageStatsSettings()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun observeSettingsAndApps() {
        viewModelScope.launch {
            settingsRepository.isAiSortingEnabled.flatMapLatest { enabled ->
                _uiState.update { it.copy(isAiEnabled = enabled) }
                aiRepository.getSortedApps(enabled)
            }.collect { result ->
                when (result) {
                    is Resource.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                    is Resource.Success -> {
                        _uiState.update { it.copy(apps = result.data, isLoading = false, error = null) }
                    }
                    is Resource.Error -> {
                        _uiState.update { it.copy(isLoading = false, error = result.exception.message) }
                        // Fallback: if AI fails, try to load basic list from AppManager
                        loadFallbackApps()
                    }
                }
            }
        }
    }

    private fun loadFallbackApps() {
        val installed = appManager.getInstalledApps()
        val fallbackEntities = installed.mapIndexed { index, info ->
            AppEntity(
                packageName = info.packageName,
                appName = info.appName,
                category = "Default",
                usageScore = 100f - index,
                lastUsedTimestamp = System.currentTimeMillis()
            )
        }
        _uiState.update { it.copy(apps = fallbackEntities, isLoading = false) }
    }

    fun syncRankings() {
        viewModelScope.launch {
            val installedApps = appManager.getInstalledApps()
            val packageNames = installedApps.map { it.packageName }
            aiRepository.updateAppRankings(packageNames)
        }
    }
}
