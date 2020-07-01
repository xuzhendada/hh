package com.xz.hh.paging

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.xz.hh.adapter.ItemCell
import com.xz.hh.adapter.RecyclerSupport
import com.xz.hh.adapter.RecyclerVH

class PageAdapter(private val support: RecyclerSupport) : PagingDataAdapter<ItemCell, RecyclerVH>(
    object : DiffUtil.ItemCallback<ItemCell>() {
        override fun areItemsTheSame(oldItem: ItemCell, newItem: ItemCell) =
            oldItem.itemId() == newItem.itemId() && oldItem.itemId() == newItem.itemId()

        override fun areContentsTheSame(oldItem: ItemCell, newItem: ItemCell) =
            oldItem.itemContent() == newItem.itemContent()
    }
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerVH {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerVH, position: Int) {
    }
}