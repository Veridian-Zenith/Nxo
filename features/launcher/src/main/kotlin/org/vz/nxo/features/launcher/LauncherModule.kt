package org.vz.nxo.features.launcher

import org.koin.dsl.module
import org.vz.nxo.core.common.AppManager
import org.vz.nxo.core.common.PermissionManager
import org.vz.nxo.core.common.SettingsRepository
import org.vz.nxo.features.aiengine.AiRepository

val launcherModule = module {
    factory { LauncherViewModel(get(), get(), get(), get()) }
}
