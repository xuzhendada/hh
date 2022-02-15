package com.hi.main.cells

import android.view.View
import com.hi.common.adapter.ItemCell
import com.hi.common.adapter.RecyclerSupport
import com.hi.common.adapter.RecyclerVH
import com.hi.common.ktx.debounceClick
import com.hi.main.R
import kotlinx.android.synthetic.main.item_btn.view.*

class BtnCell(val txtRes: String) : ItemCell {
    override fun layoutId() = R.layout.item_btn

    override fun itemId() = txtRes

    override fun itemContent() = txtRes

    override fun onCreateViewHolder(itemView: View, support: RecyclerSupport) =
        BtnVH(itemView, support)
}

class BtnVH(itemView: View, support: RecyclerSupport) : RecyclerVH(itemView, support) {
    init {
        itemView.debounceClick {
            support.simpleCallback?.invoke(layoutPosition)
        }
    }

    override fun bind(itemCell: ItemCell, payloads: MutableList<Any>) {
        super.bind(itemCell, payloads)
        if (itemCell is BtnCell) {
            itemView.btn.text = itemCell.txtRes
        }
    }
}