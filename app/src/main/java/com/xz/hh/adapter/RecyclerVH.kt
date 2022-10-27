package com.xz.hh.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class RecyclerVH (itemView: View, val support: RecyclerSupport) :
    RecyclerView.ViewHolder(itemView) {
    open fun bind(itemCell: ItemCell, payloads: MutableList<Any> = mutableListOf()) {
        //empty
    }
}