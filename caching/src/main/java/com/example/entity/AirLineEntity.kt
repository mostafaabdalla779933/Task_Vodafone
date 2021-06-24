package com.example.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.network.model.AirLineModel


@Entity
data class AirLineEntity(
    @PrimaryKey
    val id: Int,
    val established: String,
    val country: String,
    val website: String,
    val name: String,
    val headQuaters: String,
    val logo: String,
    val slogan: String,
    val createdDate: String
){

    companion object {

        private fun toEntity(model: AirLineModel): AirLineEntity =
            AirLineEntity(model.id,model.established, model.country, model.website, model.name,model.headQuaters,model.logo,model.slogan,model.createdDate)

        fun toEntityList(data: List<AirLineModel>): List<AirLineEntity> {
            val list: ArrayList<AirLineEntity> = ArrayList()
            data.forEach { list.add(toEntity(it)) }
            return list
        }
    }
}
