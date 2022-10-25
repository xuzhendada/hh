package com.hi.main.cells

import android.view.View
import android.widget.ImageView
import com.hi.common.adapter.ItemCell
import com.hi.common.adapter.RecyclerSupport
import com.hi.common.adapter.RecyclerVH
import com.hi.common.data.response.Banner
import com.hi.main.R

/**
 * @author : wbxuzhen
 * @date : 2020/11/13 13:46
 * @description :
 */
class ImgCell(val banner: Banner) : ItemCell {
    override fun layoutId() = R.layout.item_img

    override fun itemId() = banner.id.toString()

    override fun itemContent() = "$banner"

    override fun onCreateViewHolder(itemView: View, support: RecyclerSupport) =
        ImgVh(itemView, support)
}

class ImgVh(itemView: View, support: RecyclerSupport) : RecyclerVH(itemView, support) {
    override fun bind(itemCell: ItemCell, payloads: MutableList<Any>) {
        super.bind(itemCell, payloads)
        if (itemCell is ImgCell) {
            val imgView=itemView.findViewById<ImageView>(R.id.img)
            support.imageLoader?.display(imgView, itemCell.banner.imagePath)
        }
    }
}