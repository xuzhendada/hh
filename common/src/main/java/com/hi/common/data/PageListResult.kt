package com.hi.common.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class PageListResult<T>(val datas: ArrayList<T>, val pageCount: Int, val curPage: Int)