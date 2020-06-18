package com.xz.hh.ktx

import com.xz.hh.adapter.RecyclerSupport
import com.xz.hh.adapter.StableAdapter

fun createStableAdapter(support: RecyclerSupport.() -> Unit): StableAdapter {
    return StableAdapter(RecyclerSupport().apply(support))
}