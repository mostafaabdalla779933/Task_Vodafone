package com.example.task_vodafone.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.entity.AirLineEntity
import com.example.task_vodafone.R
import kotlinx.android.synthetic.main.country_layout.view.*


class AirLineAdapte( val onclick : (AirLineEntity) -> Unit) : ListAdapter<AirLineEntity, AirLineAdapte.AirLineViewHolder>(AirLineCallBack()){

    class AirLineViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        companion object {
            fun from(parent: ViewGroup):  AirLineViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.country_layout, parent, false)
                return AirLineViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirLineViewHolder {
        return AirLineViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: AirLineViewHolder, position: Int) {

        holder.itemView.tv_name.text = getItem(position).name

        holder.itemView.iv_detials.setOnClickListener {

            onclick(this.currentList.get(position))

        }
    }
}

class AirLineCallBack(): DiffUtil.ItemCallback<AirLineEntity>(){

    override fun areItemsTheSame(oldItem: AirLineEntity, newItem: AirLineEntity): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: AirLineEntity, newItem: AirLineEntity): Boolean {
        return areContentsTheSame(oldItem,newItem)
    }
}