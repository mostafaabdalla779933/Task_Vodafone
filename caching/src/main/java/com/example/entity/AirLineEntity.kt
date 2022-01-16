package com.example.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.network.model.AirLineModel


@Entity
data class AirLineEntity(
    @PrimaryKey
    val id: String,
    val established: String? = null,
    val country: String? = null,
    val website: String? = null,
    val name: String? = null,
    val headQuaters: String? = null,
    val logo: String? = null,
    val slogan: String? = null,
    val createdDate: String? = null,
    var textHighlight: String = ""
) {


    companion object {

        private fun toEntity(model: AirLineModel): AirLineEntity =
            AirLineEntity(
                model.id ?: "",
                model.established,
                model.country,
                model.website,
                model.name,
                model.headQuaters,
                model.logo,
                model.slogan,
                model.createdDate
            )

        fun toEntityList(data: List<AirLineModel>): List<AirLineEntity> {
            val list: ArrayList<AirLineEntity> = ArrayList()
            data.forEach { list.add(toEntity(it)) }
            return list
        }
    }


    override fun equals(other: Any?) =
        if (other is AirLineEntity)
            this.id == other.id && this.textHighlight == other.textHighlight && this.name == other.name
        else
            false

}
