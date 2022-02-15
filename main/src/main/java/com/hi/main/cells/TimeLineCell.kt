package com.hi.main.cells

import android.view.View
import com.hi.common.adapter.ItemCell
import com.hi.common.adapter.RecyclerSupport
import com.hi.common.adapter.RecyclerVH
import com.hi.common.ktx.debounceClick
import com.hi.main.R
import kotlinx.android.synthetic.main.item_time_line.view.*

/**
 * @author wbxuzhen
 * date : 2021/5/24 12:12
 * description : item
 */
class TimeLineCell(
    var check: Boolean,
    val title: String,
    val image: String,
    val time: String,
    val isLast: Boolean
) :
    ItemCell {
    override fun layoutId() = R.layout.item_time_line

    override fun itemId() = title

    override fun itemContent() = "${title}_${check}"

    override fun onCreateViewHolder(itemView: View, support: RecyclerSupport) =
        TimeLineVH(itemView, support)
}

class TimeLineVH(itemView: View, support: RecyclerSupport) : RecyclerVH(itemView, support) {
    init {
        itemView.linearLayout.debounceClick {
            support.timeLineCallBack?.invoke(absoluteAdapterPosition, 0)
        }
        itemView.imageView.debounceClick {
            support.timeLineCallBack?.invoke(absoluteAdapterPosition, 1)
        }
    }

    override fun bind(itemCell: ItemCell, payloads: MutableList<Any>) {
        super.bind(itemCell, payloads)
        if (itemCell is TimeLineCell) {
            itemView.imageView.visibility = if (itemCell.check) View.VISIBLE else View.GONE
            itemView.icon.setImageResource(if (itemCell.check) R.drawable.ic_time_line_select else R.drawable.ic_time_line_unselect)
            itemView.linearLayout.setBackgroundResource(if (itemCell.check) R.drawable.bg_time_line_select else R.drawable.bg_time_line_unselect)
            itemView.title.text = itemCell.title
            itemView.time.text = itemCell.time
            support.imageLoader?.display(itemView.imageView, itemCell.image)
            itemView.relative.visibility = if (itemCell.isLast) View.GONE else View.VISIBLE
        }
        if (payloads.isNotEmpty()) {
            val payload = payloads[0] as Boolean
            itemView.imageView.visibility = if (payload) View.VISIBLE else View.GONE
        }
    }
}