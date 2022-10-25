package com.hi.main.cells

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.hi.common.adapter.ItemCell
import com.hi.common.adapter.RecyclerSupport
import com.hi.common.adapter.RecyclerVH
import com.hi.common.ktx.sameAs
import com.hi.common.room.entity.StudentEntity
import com.hi.main.R

/**
 *  Created by wbxuzhen on 2020/11/20 15:11.
 *  des:room的item
 */
class RoomCell(val studentEntity: StudentEntity) : ItemCell {
    override fun layoutId() = R.layout.item_room

    override fun itemId() = studentEntity.name

    override fun itemContent() = "$studentEntity"

    override fun onCreateViewHolder(itemView: View, support: RecyclerSupport) =
        RoomVH(itemView, support)
}

class RoomVH(itemView: View, support: RecyclerSupport) : RecyclerVH(itemView, support) {

    override fun bind(itemCell: ItemCell, payloads: MutableList<Any>) {
        super.bind(itemCell, payloads)
        itemCell.sameAs<RoomCell> {
            itemView.findViewById<AppCompatTextView>(R.id.name).text = it.studentEntity.name
        }
    }
}