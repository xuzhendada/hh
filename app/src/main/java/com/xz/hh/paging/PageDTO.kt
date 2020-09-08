package com.xz.hh.paging

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PageDTO<T>(
    var curPage: Int = 0,
    var datas: MutableList<T> = mutableListOf(),
    var offset: Int = 0,
    var over: Boolean = true,
    var pageCount: Int = 0,
    var size: Int = 0,
    var total: Int = 0
)