package org.vz.nxo.core.common

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Build

class AppManager(private val context: Context) {
    fun getInstalledApps(): List<AppInfo> {
        val pm = context.packageManager
        
        val apps = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            pm.getInstalledApplications(PackageManager.ApplicationInfoFlags.of(
                PackageManager.GET_META_DATA.toLong()
            ))
        } else {
            pm.getInstalledApplications(PackageManager.GET_META_DATA)
        }
        
        return apps.filter { info ->
            pm.getLaunchIntentForPackage(info.packageName) != null
        }.map { 
            AppInfo(
                packageName = it.packageName,
                appName = pm.getApplicationLabel(it).toString(),
                iconRes = it.loadIcon(pm)
            )
        }
    }
}

data class AppInfo(
    val packageName: String,
    val appName: String,
    val iconRes: android.graphics.drawable.Drawable?
)
