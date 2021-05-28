package com.hi.main.cells

import android.view.View
import com.hi.common.adapter.ItemCell
import com.hi.common.adapter.RecyclerSupport
import com.hi.common.adapter.RecyclerVH
import com.hi.main.R
import kotlinx.android.synthetic.main.item_flow_layout.view.*

/**
 * @author wbxuzhen
 * date : 2021/5/28 10:19
 * description :
 */
class FlowLayoutCell(val name: String) : ItemCell {
    override fun layoutId() = R.layout.item_flow_layout

    override fun itemId() = "FlowLayoutCell"

    override fun itemContent() = name

    override fun onCreateViewHolder(itemView: View, support: RecyclerSupport) =
        FlowLayoutVH(itemView, support)
}

class FlowLayoutVH(itemView: View, support: RecyclerSupport) : RecyclerVH(itemView, support) {
    override fun bind(itemCell: ItemCell, payloads: MutableList<Any>) {
        super.bind(itemCell, payloads)
        if (itemCell is FlowLayoutCell) {
            itemView.txStudentName.text = itemCell.name
        }
    }
}