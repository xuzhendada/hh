package com.hi.main.cells

import android.net.Uri
import android.view.View
import android.widget.ImageView
import com.hi.common.adapter.ItemCell
import com.hi.common.adapter.RecyclerSupport
import com.hi.common.adapter.RecyclerVH
import com.hi.common.ktx.sameAs
import com.hi.main.R

/**
 *  Created by wbxuzhen on 2020/11/19 16:02.
 *  des:
 */
class SelectImgCell( val uri: Uri) : ItemCell {
    override fun layoutId() = R.layout.item_select_img

    override fun itemId() = uri.toString()

    override fun itemContent() = uri.toString()

    override fun onCreateViewHolder(itemView: View, support: RecyclerSupport) =
        SelectImgVH(itemView, support)
}

class SelectImgVH(itemView: View, support: RecyclerSupport) : RecyclerVH(itemView, support) {

    override fun bind(itemCell: ItemCell, payloads: MutableList<Any>) {
        itemCell.sameAs<SelectImgCell> {
            val imgView = itemView.findViewById<ImageView>(R.id.img)
            support.imageLoader?.display(imgView, it.uri)
        }
    }

}