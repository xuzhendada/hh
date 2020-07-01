package com.xz.hh.cells

import android.view.View
import com.xz.hh.R
import com.xz.hh.adapter.ItemCell
import com.xz.hh.adapter.RecyclerSupport
import com.xz.hh.adapter.RecyclerVH
import com.xz.hh.data.response.ListResponse
import com.xz.hh.ktx.debounceClick
import kotlinx.android.synthetic.main.item_wan.view.*

class WanCell(val listResponse: ListResponse) : ItemCell {

    override fun layoutId() = R.layout.item_wan

    override fun itemId() = "${listResponse.name}_${listResponse.order}"

    override fun itemContent() = listResponse.toString()

    override fun onCreateViewHolder(itemView: View, support: RecyclerSupport) =
        WanVH(itemView, support)
}

class WanVH(itemView: View, support: RecyclerSupport) : RecyclerVH(itemView, support) {
    init {
        itemView.debounceClick {
            support.simpleCallback?.invoke(layoutPosition)
        }
    }

    override fun bind(itemCell: ItemCell, payloads: MutableList<Any>) {
        super.bind(itemCell, payloads)
        if (itemCell is WanCell) {
            itemView.wanName.text = itemCell.listResponse.name
            itemView.wanOrder.text = "${itemCell.listResponse.order}"
        }
    }
}