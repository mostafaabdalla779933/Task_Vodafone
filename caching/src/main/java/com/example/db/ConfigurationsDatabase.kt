package com.evaph.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.entity.AirLineEntity

@Database(version = 1, entities = [AirLineEntity::class])
abstract class ConfigurationsDatabase : RoomDatabase() {
    abstract fun getAirLineDao(): AirlLineDao
}