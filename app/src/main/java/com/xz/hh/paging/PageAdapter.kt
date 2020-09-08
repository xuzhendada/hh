package com.xz.hh.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.xz.hh.adapter.ItemCell
import com.xz.hh.adapter.RecyclerSupport
import com.xz.hh.adapter.RecyclerVH
import java.lang.IllegalArgumentException

class PageAdapter(private val support: RecyclerSupport) :
    PagingDataAdapter<ItemCell, RecyclerVH>(DIFF) {
    private val differ = AsyncListDiffer(this, DIFF)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerVH {
        differ.currentList.forEach {
            if (viewType == it.layoutId()) {
                return it.onCreateViewHolder(
                    LayoutInflater.from(parent.context).inflate(viewType, parent, false), support
                )
            }
        }
        throw IllegalArgumentException("viewType not found")
    }

    override fun onBindViewHolder(holder: RecyclerVH, position: Int) {
        //empty
    }

    override fun getItemViewType(position: Int) = differ.currentList[position].layoutId()

    override fun onBindViewHolder(holder: RecyclerVH, position: Int, payloads: MutableList<Any>) {
        holder.bind(differ.currentList[position], payloads)
    }

    fun submitList(temp: List<ItemCell>, callback: () -> Unit = {}) {
        differ.submitList(temp)
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