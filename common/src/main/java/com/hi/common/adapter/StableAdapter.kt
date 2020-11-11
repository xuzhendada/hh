package com.hi.common.adapter

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.IntRange
import androidx.core.util.set
import androidx.recyclerview.widget.*
import com.hi.common.widget.DefaultEmptyCell
import java.lang.IllegalArgumentException

class StableAdapter(
    private val support: RecyclerSupport,
    config: AsyncDifferConfig<ItemCell> = AsyncDifferConfig.Builder(object :
        DiffUtil.ItemCallback<ItemCell>() {

        override fun areItemsTheSame(oldItem: ItemCell, newItem: ItemCell) =
            oldItem.layoutId() == newItem.layoutId() && oldItem.itemId() == newItem.itemId()

        override fun areContentsTheSame(oldItem: ItemCell, newItem: ItemCell) =
            oldItem.itemContent() == newItem.itemContent()

    }).build()
) : RecyclerView.Adapter<RecyclerVH>() {
    private val differ = AsyncListDiffer(AdapterListUpdateCallback(this), config)
    private val keyList = mutableListOf<Int>()
    private val sparseArray by lazy {
        SparseArray<List<ItemCell>>()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerVH {
        differ.currentList.forEach {
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

    override fun getItemViewType(position: Int) = differ.currentList[position].layoutId()

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: RecyclerVH, position: Int, payloads: MutableList<Any>) {
        holder.bind(differ.currentList[position], payloads)
    }

    override fun onBindViewHolder(holder: RecyclerVH, position: Int) {
        //empty
    }

    fun currentList(): MutableList<ItemCell> = differ.currentList

    fun submitList(
        @IntRange(from = 0) position: Int, temp: List<ItemCell>,
        clearOldData: Boolean = false
    ) {
        if (clearOldData) {
            keyList.clear()
            sparseArray.clear()
        }
        if (!keyList.contains(position)) {
            keyList.add(position)
        }
        sparseArray[position] = temp

        val resultList = mutableListOf<ItemCell>()
        keyList.sorted().forEach {
            resultList.addAll(sparseArray[it, mutableListOf()])
        }
        differ.submitList(resultList)
    }

    fun submitList(temp: List<ItemCell>, callback: () -> Unit = {}) {
        differ.submitList(temp)
        callback.invoke()
    }

    /**
     * 提交列表数据
     * @param pageIndex 页码
     * @param list 列表数据
     * @param emptyCell 空页面
     * @param callback 加载完成回调
     *
     */
    fun submitList(
        @IntRange(from = 1) pageIndex: Int,
        list: List<ItemCell>,
        emptyCell: ItemCell = DefaultEmptyCell(),
        callback: () -> Unit = {}
    ) {
        when (pageIndex) {
            1 -> {
                if (list.isEmpty()) {
                    differ.submitList(mutableListOf(emptyCell)) {
                        callback.invoke()
                    }
                } else {
                    differ.submitList(list) {
                        callback.invoke()
                    }
                }
            }
            else -> {
                val temp = mutableListOf<ItemCell>()
                temp.addAll(differ.currentList)
                if (list.isEmpty()) {
                    callback.invoke()
                } else {
                    temp.addAll(list)
                    differ.submitList(temp) {
                        callback.invoke()
                    }
                }
            }
        }
    }
}