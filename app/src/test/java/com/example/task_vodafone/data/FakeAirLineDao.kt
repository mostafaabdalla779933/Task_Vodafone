package com.example.task_vodafone.data

import com.evaph.database.db.AirlLineDao
import com.example.entity.AirLineEntity

class FakeAirLineDao  : AirlLineDao{
    override suspend fun insertAirlLines(list: List<AirLineEntity>) {

    }

    override suspend fun insertAirlLine(airline: AirLineEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun getAirlLines(): List<AirLineEntity> {
        return listOf(AirLineEntity("1","","egypt","","cairo"))
    }

    override suspend fun getAirlLineByEnName(name: String): List<AirLineEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun getAirlLineById(id: Int): List<AirLineEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun getAirlLineByCountryName(country: String): List<AirLineEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun clearAirlLines() {
        TODO("Not yet implemented")
    }
}