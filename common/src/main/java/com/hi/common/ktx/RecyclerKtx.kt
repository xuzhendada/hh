package com.hi.common.ktx

import com.hi.common.adapter.RecyclerSupport
import com.hi.common.adapter.StableAdapter

fun createStableAdapter(support: RecyclerSupport.() -> Unit): StableAdapter {
    return StableAdapter(RecyclerSupport().apply(support))
}

//fun createPageAdapter(support: RecyclerSupport.() -> Unit): PageAdapter {
//    return PageAdapter(RecyclerSupport().apply(support))
//}