package com.xz.hh.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import com.xz.hh.adapter.ItemCell
import com.xz.hh.adapter.RecyclerSupport
import com.xz.hh.adapter.RecyclerVH

class PageAdapter(private val support: RecyclerSupport, context: Context) :
    PagingDataAdapter<ItemCell, RecyclerVH>(DIFF) {

    private val layoutInflater by lazy {
        LayoutInflater.from(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerVH {
        return RecyclerVH(
            itemView = layoutInflater.inflate(viewType, parent, false),
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
        private val config: AsyncDifferConfig<ItemCell> = AsyncDifferConfig.Builder(
            object : DiffUtil.ItemCallback<ItemCell>() {
                override fun areItemsTheSame(oldItem: ItemCell, newItem: ItemCell): Boolean {
                    return oldItem.itemId() == newItem.itemId() && oldItem.itemId() == newItem.itemId()
                }

                override fun areContentsTheSame(oldItem: ItemCell, newItem: ItemCell): Boolean {
                    return oldItem.itemContent() == newItem.itemContent()
                }
            }
        ).build()
        private val DIFF = object : DiffUtil.ItemCallback<ItemCell>() {
            override fun areItemsTheSame(oldItem: ItemCell, newItem: ItemCell) =
                oldItem.itemId() == newItem.itemId() && oldItem.itemId() == newItem.itemId()

            override fun areContentsTheSame(oldItem: ItemCell, newItem: ItemCell) =
                oldItem.itemContent() == newItem.itemContent()
        }
    }
}