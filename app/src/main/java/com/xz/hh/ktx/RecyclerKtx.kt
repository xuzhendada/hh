package com.xz.hh.ktx

import com.xz.hh.adapter.RecyclerSupport
import com.xz.hh.adapter.StableAdapter
import com.xz.hh.paging.PageAdapter

fun createStableAdapter(support: RecyclerSupport.() -> Unit): StableAdapter {
    return StableAdapter(RecyclerSupport().apply(support))
}

fun createPageAdapter(support: RecyclerSupport.() -> Unit): PageAdapter {
    return PageAdapter(RecyclerSupport().apply(support))
}