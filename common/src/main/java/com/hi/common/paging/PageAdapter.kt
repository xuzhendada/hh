package com.hi.common.paging

import android.util.Log
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.AsyncPagedListDiffer
import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.hi.common.adapter.ItemCell
import com.hi.common.adapter.RecyclerSupport
import com.hi.common.adapter.RecyclerVH
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.forEach
import java.lang.IllegalArgumentException

class PageAdapter(private val support: RecyclerSupport) :
    PagingDataAdapter<ItemCell, RecyclerVH>(
        POST_COMPARATOR
    ) {
    override fun onBindViewHolder(holder: RecyclerVH, position: Int) {
        pageDiff.getItem(position)?.let { holder.bind(it) }
    }

    override fun getItemViewType(position: Int): Int {
        return diff.currentList?.get(position)?.layoutId()!!
    }

    override fun onBindViewHolder(holder: RecyclerVH, position: Int, payloads: MutableList<Any>) {
        pageDiff.getItem(position)?.let { holder.bind(it, payloads) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerVH {
        diff.currentList?.forEach {
            if (viewType == it.layoutId()) {
                return it.onCreateViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        viewType, parent, false
                    ), support
                )
            }
        }
        throw IllegalArgumentException("viewType not found")
    }

    suspend fun currentList() = pageDiff.loadStateFlow.collect {
        it.forEach { loadType, b, loadState ->
            Log.e("TAG", "$loadType $b $loadState")
        }
    }

    private val diff = AsyncPagedListDiffer(
        AdapterListUpdateCallback(this),
        AsyncDifferConfig.Builder(POST_COMPARATOR).build()
    )

    private val differ = AsyncListDiffer(
        AdapterListUpdateCallback(this),
        AsyncDifferConfig.Builder(POST_COMPARATOR).build()
    )
    private val pageDiff =
        AsyncPagingDataDiffer(
            Dispatchers.Main, Dispatchers.Default, POST_COMPARATOR,
            AdapterListUpdateCallback(this)
        )

    private val keyList = mutableListOf<Int>()
    private val sparseArray by lazy {
        SparseArray<List<ItemCell>>()
    }

    companion object {
        val POST_COMPARATOR = object : DiffUtil.ItemCallback<ItemCell>() {
            override fun areItemsTheSame(oldItem: ItemCell, newItem: ItemCell): Boolean {
                return oldItem.layoutId() == newItem.layoutId() && oldItem.itemId() == newItem.itemId()
            }

            override fun areContentsTheSame(oldItem: ItemCell, newItem: ItemCell): Boolean {
                return oldItem.itemContent() == newItem.itemContent()
            }
        }
    }
}