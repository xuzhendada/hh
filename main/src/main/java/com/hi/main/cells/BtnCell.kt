package com.hi.main.cells

import android.view.View
import androidx.appcompat.widget.AppCompatButton
import com.hi.common.adapter.ItemCell
import com.hi.common.adapter.RecyclerSupport
import com.hi.common.adapter.RecyclerVH
import com.hi.common.ktx.debounceClick
import com.hi.common.ktx.sameAs
import com.hi.main.R

class BtnCell(val txtRes: String) : ItemCell {
    override fun layoutId() = R.layout.item_btn

    override fun itemId() = txtRes

    override fun itemContent() = txtRes

    override fun onCreateViewHolder(itemView: View, support: RecyclerSupport) =
        BtnVH(itemView, support)
}

class BtnVH(itemView: View, support: RecyclerSupport) : RecyclerVH(itemView, support) {
    private var btnView: AppCompatButton

    init {
        btnView = itemView.findViewById(R.id.btn)
        itemView.debounceClick {
            support.simpleCallback?.invoke(layoutPosition)
        }
    }

    override fun bind(itemCell: ItemCell, payloads: MutableList<Any>) {
        super.bind(itemCell, payloads)
        itemCell.sameAs<BtnCell> {
            btnView.text=it.txtRes
        }
    }
}