package com.hi.main.cells

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.widget.AppCompatTextView
import com.hi.common.adapter.ItemCell
import com.hi.common.adapter.RecyclerSupport
import com.hi.common.adapter.RecyclerVH
import com.hi.common.ktx.debounceClick
import com.hi.main.R

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
    private var linearLayout: LinearLayout
    private var imageView: ImageView
    private var icon: ImageView
    private var title: AppCompatTextView
    private var time: AppCompatTextView
    private var relative: RelativeLayout

    init {
        linearLayout = itemView.findViewById(R.id.linearLayout)
        imageView = itemView.findViewById(R.id.imageView)
        icon = itemView.findViewById(R.id.icon)
        title = itemView.findViewById(R.id.title)
        time = itemView.findViewById(R.id.time)
        relative = itemView.findViewById(R.id.relative)
        linearLayout.debounceClick {
            support.timeLineCallBack?.invoke(absoluteAdapterPosition, 0)
        }
        imageView.debounceClick {
            support.timeLineCallBack?.invoke(absoluteAdapterPosition, 1)
        }
    }

    override fun bind(itemCell: ItemCell, payloads: MutableList<Any>) {
        super.bind(itemCell, payloads)
        if (itemCell is TimeLineCell) {
            imageView.visibility = if (itemCell.check) View.VISIBLE else View.GONE
            icon.setImageResource(if (itemCell.check) R.drawable.ic_time_line_select else R.drawable.ic_time_line_unselect)
            linearLayout.setBackgroundResource(if (itemCell.check) R.drawable.bg_time_line_select else R.drawable.bg_time_line_unselect)
            title.text = itemCell.title
            time.text = itemCell.time
            support.imageLoader?.display(imageView, itemCell.image)
            relative.visibility = if (itemCell.isLast) View.GONE else View.VISIBLE
        }
        if (payloads.isNotEmpty()) {
            val payload = payloads[0] as Boolean
            imageView.visibility = if (payload) View.VISIBLE else View.GONE
        }
    }
}