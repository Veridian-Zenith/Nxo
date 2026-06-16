package org.vz.nxo.features.aiengine.db

import androidx.room.Database
import androidx.room.RoomDatabase
import org.vz.nxo.features.aiengine.db.AppDao
import org.vz.nxo.features.aiengine.db.AppEntity

@Database(entities = [AppEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}
