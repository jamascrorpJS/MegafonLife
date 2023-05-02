package com.jamascrorp.megafonlife.test

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jamascrorp.megafonlife.R
import com.jamascrorp.megafonlife.calendar_fragment.CalendarFragmentViewModel
import com.jamascrorp.megafonlife.databinding.ListofdayBinding

class DaysAdapter : ListAdapter<Day, DaysAdapter.DayViewHolder>(DaysDiffUtil()) {

    var onItemClick: ((day: Day)-> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder =
        DayViewHolder(
            ListofdayBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class DayViewHolder(
        private val binding: ListofdayBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(getItem(adapterPosition))
            }
        }

        fun bind(item: Day) {
            //TODO:2) use this one!
//            binding.text.text = if (item.day == 0) {
//                ""
//            } else {
//                item.day.toString()
//            }


            //TODO: 1) this is for checking, do not use in product
            if(item.isSelected && item.isStart || item.isEnd) {
                binding.text.text = "x"
            } else if (item.isSelected) {
                binding.text.text = "xx"
            }
            else {
                    binding.text.text = if (item.day == 0) {
                        ""
                    } else {
                        item.day.toString()
                    }
                }

//            if (item.isSelected) {
//                binding.text.setTextColor(
//                    ContextCompat.getColor(
//                        itemView.context,
//                        R.color.white
//                    )
//                )
//                binding.cards.setBackgroundResource(R.drawable.selecte)
//            } else {
//                binding.cards.setCardBackgroundColor(
//                    ContextCompat.getColor(
//                        itemView.context,
//                        R.color.white
//                    )
//                )
//                binding.text.setTextColor(
//                    ContextCompat.getColor(
//                        itemView.context,
//                        R.color.black
//                    )
//                )
//                binding.cards.setBackgroundResource(R.drawable.selecte1)
//            }
        }
    }

    class DaysDiffUtil : DiffUtil.ItemCallback<Day>() {
        override fun areItemsTheSame(oldItem: Day, newItem: Day): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Day, newItem: Day): Boolean {
            return oldItem.isSelected == newItem.isSelected
        }
    }
}