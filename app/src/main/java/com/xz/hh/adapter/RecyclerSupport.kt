package com.xz.hh.adapter

import com.xz.hh.ktx.ImageLoader


class RecyclerSupport {
    var imageLoader: ImageLoader? = null

    var simpleCallback: ((position: Int) -> Unit)? = null

    infix fun onSimpleCallback(block: (position: Int) -> Unit) {
        simpleCallback = block
    }
}