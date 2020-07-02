package com.xz.hh.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.xz.hh.adapter.ItemCell
import com.xz.hh.adapter.RecyclerSupport
import com.xz.hh.adapter.RecyclerVH

class PageAdapter(private val support: RecyclerSupport) :
    PagingDataAdapter<ItemCell, RecyclerVH>(DIFF) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerVH {
        return RecyclerVH(
            itemView = LayoutInflater.from(parent.context).inflate(viewType, parent, false),
            support = support
        )
    }

    override fun onBindViewHolder(holder: RecyclerVH, position: Int) {
        //empty
    }

    override fun getItemViewType(position: Int) = getItem(position)!!.layoutId()

    override fun onBindViewHolder(holder: RecyclerVH, position: Int, payloads: MutableList<Any>) {
        holder.bind(getItem(position)!!, payloads)
    }

    companion object {
        private val DIFF = object : DiffUtil.ItemCallback<ItemCell>() {
            override fun areItemsTheSame(oldItem: ItemCell, newItem: ItemCell) =
                oldItem.itemId() == newItem.itemId()

            override fun areContentsTheSame(oldItem: ItemCell, newItem: ItemCell) =
                oldItem.itemContent() == newItem.itemContent()
        }
    }
}