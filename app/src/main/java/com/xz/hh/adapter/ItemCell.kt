package com.xz.hh.adapter

import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

interface ItemCell {
    @LayoutRes
    fun layoutId(): Int

    fun itemId(): String

    fun itemContent(): String

    fun onCreateViewHolder(itemView: View, support: RecyclerSupport): RecyclerVH
}