package com.example.task_vodafone.ui.airlines.view


import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.entity.AirLineEntity
import com.example.task_vodafone.databinding.CountryLayoutBinding


class AirLineAdapte( val onclick : (AirLineEntity) -> Unit) : ListAdapter<AirLineEntity, AirLineAdapte.AirLineViewHolder>(
    Callback
){

   inner class AirLineViewHolder (private val rowView: CountryLayoutBinding) : RecyclerView.ViewHolder(rowView.root) {
        fun onBind(item:AirLineEntity,position:Int){
            item.name?.let { str->
                rowView.tvName.text=str
                if (item.textHighlight.isNotEmpty())
                    setHighLightedText(rowView.tvName,item.textHighlight)
            }
            rowView.root.setOnClickListener {
                if (position + 1 <= currentList.size)
                    onclick(getItem(position))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AirLineViewHolder {
        return AirLineViewHolder(CountryLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: AirLineViewHolder, position: Int) {
        holder.onBind(getItem(position),position)
    }


    companion object {
        private val Callback = object : DiffUtil.ItemCallback<AirLineEntity>() {
            override fun areItemsTheSame(oldItem: AirLineEntity, newItem: AirLineEntity): Boolean =
                oldItem.equals(newItem)

            override fun areContentsTheSame(oldItem: AirLineEntity, newItem: AirLineEntity): Boolean =
                oldItem.equals(newItem)
        }
    }

    private fun setHighLightedText(tv: TextView, textToHighlight: String) {

        val wordToSpan: Spannable = SpannableString(tv.text)

        wordToSpan.setSpan(
            BackgroundColorSpan(-0x100),
            0,
            tv.text.indexOf(textToHighlight.last(), 0,true) + 1,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tv.setText(wordToSpan, TextView.BufferType.SPANNABLE)
    }

}



