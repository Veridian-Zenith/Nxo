package org.vz.nxo

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.vz.nxo.core.common.coreModule
import org.vz.nxo.features.aiengine.aiEngineModule
import org.vz.nxo.features.launcher.launcherModule
import org.vz.nxo.features.settings.settingsModule

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                listOf(
                    coreModule,
                    aiEngineModule,
                    launcherModule,
                    settingsModule
                )
            )
        }
    }
}
