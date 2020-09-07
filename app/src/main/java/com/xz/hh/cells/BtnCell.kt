package com.xz.hh.cells

import android.view.View
import androidx.annotation.StringRes
import com.xz.hh.R
import com.xz.hh.adapter.ItemCell
import com.xz.hh.adapter.RecyclerSupport
import com.xz.hh.adapter.RecyclerVH
import com.xz.hh.ktx.debounceClick
import kotlinx.android.synthetic.main.item_btn.view.*
import org.jetbrains.anko.textResource

class BtnCell(@StringRes val txtRes: Int) : ItemCell {
    override fun layoutId() = R.layout.item_btn

    override fun itemId() = "$txtRes"

    override fun itemContent() = "$txtRes"

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
            itemView.btn.textResource = itemCell.txtRes
        }
    }
}