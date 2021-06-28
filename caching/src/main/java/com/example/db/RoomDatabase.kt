package com.example.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.db.AirlLineDao
import com.example.entity.AirLineEntity

@Database(version = 1, entities = [AirLineEntity::class])
abstract class RoomDatabase : RoomDatabase() {
    abstract fun getAirLineDao(): AirlLineDao
}