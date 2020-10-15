package com.hi.common.ktx

import com.hi.common.adapter.RecyclerSupport
import com.hi.common.adapter.StableAdapter
//import com.hi.main.PageAdapter

fun createStableAdapter(support: RecyclerSupport.() -> Unit): StableAdapter {
    return StableAdapter(RecyclerSupport().apply(support))
}

//fun createPageAdapter(support: RecyclerSupport.() -> Unit): com.hi.main.PageAdapter {
//    return com.hi.main.PageAdapter(RecyclerSupport().apply(support))
//}