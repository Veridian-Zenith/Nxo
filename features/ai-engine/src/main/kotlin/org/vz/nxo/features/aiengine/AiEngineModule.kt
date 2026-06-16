package org.vz.nxo.features.aiengine

import android.content.Context
import androidx.room.Room
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.vz.nxo.core.common.SettingsRepository
import org.vz.nxo.features.aiengine.db.AppDatabase

val aiEngineModule = module {
    single { 
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, 
            "nxo_ai_db"
        ).build() 
    }
    single { get<AppDatabase>().appDao() }
    
    single { 
        AiEngineClient() 
    }
    
    single<AiRepository> { AiRepositoryImpl(get(), get(), get()) }
}
