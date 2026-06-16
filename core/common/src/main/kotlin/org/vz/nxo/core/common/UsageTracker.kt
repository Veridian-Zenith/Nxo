package org.vz.nxo.core.common

import android.app.Application
import android.app.usage.UsageStatsManager
import android.content.Context
import android.os.Build
import android.util.Log
import android.annotation.SuppressLint

class UsageTracker(private val context: Context) {
    @SuppressLint("MissingPermission")
    fun getTopApps(limit: Int): List<Pair<String, Long>> {
        // Check for permission first to avoid crash
        val usm = context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val endTime = System.currentTimeMillis()
        val startTime = endTime - (1000 * 60 * 60 * 24 * 7) // Last 7 days

        return try {
            val stats = usm.queryUsageStats(
                UsageStatsManager.INTERVAL_DAILY, 
                startTime, 
                endTime
            )
            stats?.sortedByDescending { it.totalTimeInForeground }
                ?.take(limit)
                ?.map { it.packageName to it.totalTimeInForeground }
                ?: emptyList()
        } catch (e: SecurityException) {
            Log.w("UsageTracker", "PACKAGE_USAGE_STATS permission not granted")
            emptyList()
        } catch (e: Exception) {
            Log.e("UsageTracker", "Failed to query usage stats", e)
            emptyList()
        }
    }
}
