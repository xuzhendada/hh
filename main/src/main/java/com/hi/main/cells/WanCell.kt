package com.hi.main.cells

import android.view.View
import com.hi.common.adapter.ItemCell
import com.hi.common.adapter.RecyclerSupport
import com.hi.common.adapter.RecyclerVH
import com.hi.common.data.response.ListResponse
import com.hi.common.ktx.debounceClick
import com.hi.main.R
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