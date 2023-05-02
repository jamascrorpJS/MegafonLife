package com.jamascrorp.megafonlife.test

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jamascrorp.megafonlife.databinding.ListofmonthBinding

class MonthsAdapter : RecyclerView.Adapter<MonthsAdapter.ViewHolder>() {

    var onDayClick: ((Day) -> Unit)? = null
    var items: List<Month> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater =
            ListofmonthBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(layoutInflater)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    inner class ViewHolder(
        private val binding: ListofmonthBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Month) {
            val adapter = DaysAdapter()
            adapter.submitList(item.days)
            adapter.onItemClick = onDayClick

            // UI
            binding.month.text = "${item.name}, 2023"
            binding.rv.adapter = adapter
            binding.rv.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.VERTICAL, false)
            binding.rv.layoutManager = GridLayoutManager(itemView.context, 7)
        }
    }

//    class MonthDiffUtil : DiffUtil.ItemCallback<Month>() {
//        override fun areItemsTheSame(oldItem: Month, newItem: Month): Boolean {
//            return oldItem == newItem
//        }
//
//        override fun areContentsTheSame(oldItem: Month, newItem: Month): Boolean {
//            var i = 0
//            while (i < oldItem.days.count()) {
//                if (oldItem.days[i].isSelected != newItem.days[i].isSelected) {
//                    return false
//                }
//                i++
//            }
//            return true
//        }
//    }
}