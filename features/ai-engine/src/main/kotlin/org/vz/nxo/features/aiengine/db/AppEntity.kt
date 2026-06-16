package org.vz.nxo.features.aiengine.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "apps")
data class AppEntity(
    @PrimaryKey val packageName: String,
    val appName: String,
    val category: String,
    val usageScore: Float,
    val lastUsedTimestamp: Long
)
