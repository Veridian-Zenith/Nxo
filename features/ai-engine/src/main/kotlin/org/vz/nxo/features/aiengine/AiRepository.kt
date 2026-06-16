package org.vz.nxo.features.aiengine

import kotlinx.coroutines.flow.*
import org.vz.nxo.core.common.Resource
import org.vz.nxo.core.common.SettingsRepository
import org.vz.nxo.features.aiengine.db.AppDao
import org.vz.nxo.features.aiengine.db.AppEntity

interface AiRepository {
    fun getSortedApps(isAiEnabled: Boolean): Flow<Resource<List<AppEntity>>>
    suspend fun updateAppRankings(appsList: List<String>)
}

class AiRepositoryImpl(
    private val appDao: AppDao,
    private val aiClient: AiEngineClient,
    private val settingsRepository: SettingsRepository
) : AiRepository {
    
    override fun getSortedApps(isAiEnabled: Boolean): Flow<Resource<List<AppEntity>>> = 
        appDao.getAllApps().map { apps ->
            if (apps.isEmpty()) {
                // If DB is empty, we are still loading or need a sync
                Resource.Loading 
            } else {
                Resource.Success(apps)
            }
        }.onStart { 
            emit(Resource.Loading) 
        }.catch { e -> 
            emit(Resource.Error(e)) 
        }

    override suspend fun updateAppRankings(appsList: List<String>) {
        val enabled = settingsRepository.isAiSortingEnabled.first()
        if (!enabled) return

        val apiKey = settingsRepository.geminiApiKey.first()
        if (apiKey.isBlank()) return

        try {
            val sortedPackageNames = aiClient.suggestAppSorting(apiKey, appsList)
            
            val entities = sortedPackageNames.mapIndexed { index, pkg ->
                AppEntity(
                    packageName = pkg,
                    appName = pkg,
                    category = "AI Sorted",
                    usageScore = 100f - index,
                    lastUsedTimestamp = System.currentTimeMillis()
                )
            }
            
            appDao.clearAll()
            appDao.insertApps(entities)
        } catch (e: Exception) {
            // Log error
        }
    }
}
