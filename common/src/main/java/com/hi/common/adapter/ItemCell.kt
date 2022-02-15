package com.hi.common.adapter

import android.view.View
import androidx.annotation.LayoutRes
import com.hi.common.adapter.RecyclerSupport
import com.hi.common.adapter.RecyclerVH

interface ItemCell {
    @LayoutRes
    fun layoutId(): Int

    fun itemId(): String

    fun itemContent(): String

    fun onCreateViewHolder(itemView: View, support: RecyclerSupport): RecyclerVH
}