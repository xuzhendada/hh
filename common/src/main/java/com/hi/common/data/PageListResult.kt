package com.hi.common.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class PageListResult<T>(
    val datas: MutableList<T>,
    val pageCount: Int,
    val curPage: Int,
    val offset: Int = 0,
    val over: Boolean = false,
    val size: Int,
    val total: Int
)