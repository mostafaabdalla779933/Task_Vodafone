package com.evaph.database.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.entity.AirLineEntity

@Dao
interface AirlLineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAirlLines(list: List<AirLineEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAirlLine(airline: AirLineEntity)

    @Query("SELECT * FROM AirLineEntity")
    suspend fun getAirlLines(): List<AirLineEntity>

    @Query("SELECT * FROM AirLineEntity WHERE name like :name")
    suspend fun getAirlLineByEnName(name: String): List<AirLineEntity>

    @Query("SELECT * FROM AirLineEntity WHERE id like :id")
    suspend fun getAirlLineById(id: Int): List<AirLineEntity>

    @Query("SELECT * FROM AirLineEntity WHERE country like :country")
    suspend fun getAirlLineByCountryName(country: String): List<AirLineEntity>

    @Query("DELETE FROM AirLineEntity")
    suspend fun clearAirlLines()
}
