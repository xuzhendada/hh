package com.xz.hh.adapter

import android.view.View
import androidx.annotation.LayoutRes

interface ItemCell {
    @LayoutRes
    fun layoutId(): Int

    fun itemId(): String

    fun itemContent(): String

    fun onCreateViewHolder(itemView: View, support: RecyclerSupport): RecyclerVH
}