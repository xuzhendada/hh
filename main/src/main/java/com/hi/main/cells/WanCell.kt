package com.hi.main.cells

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.hi.common.adapter.ItemCell
import com.hi.common.adapter.RecyclerSupport
import com.hi.common.adapter.RecyclerVH
import com.hi.common.data.response.ListResponse
import com.hi.common.ktx.debounceClick
import com.hi.common.ktx.sameAs
import com.hi.main.R

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
        itemCell.sameAs<WanCell> {
            val wanNameView = itemView.findViewById<AppCompatTextView>(R.id.wanName)
            val wanOrderView = itemView.findViewById<AppCompatTextView>(R.id.wanOrder)
            wanNameView.text = it.listResponse.name
            wanOrderView.text = it.listResponse.order.toString()
        }
    }
}