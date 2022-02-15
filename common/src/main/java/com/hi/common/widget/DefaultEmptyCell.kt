package com.hi.common.widget

import android.view.View
import com.hi.common.R
import com.hi.common.adapter.ItemCell
import com.hi.common.adapter.RecyclerSupport
import com.hi.common.adapter.RecyclerVH

/**
 * @author : wbxuzhen
 * @date : 2020/11/11 13:55
 * @description :数据为空展示
 */
class DefaultEmptyCell : ItemCell {
    override fun layoutId() = R.layout.default_empty_cell

    override fun itemId() = "DefaultEmptyCell"

    override fun itemContent() = "DefaultEmptyCell"

    override fun onCreateViewHolder(itemView: View, support: RecyclerSupport) =
        DefaultEmptyVH(itemView, support)
}

class DefaultEmptyVH(itemView: View, support: RecyclerSupport) : RecyclerVH(itemView, support)