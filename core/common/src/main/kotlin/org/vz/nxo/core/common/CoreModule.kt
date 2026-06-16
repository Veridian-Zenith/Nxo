package org.vz.nxo.core.common

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreModule = module {
    single { AppManager(androidContext()) }
    single { UsageTracker(androidContext()) }
    single { PermissionManager(androidContext()) }
    single<SettingsRepository> { SettingsRepositoryImpl(androidContext()) }
}

