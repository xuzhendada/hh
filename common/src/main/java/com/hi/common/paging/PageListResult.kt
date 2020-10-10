package com.hi.common.paging

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class PageListResult<T>(val datas: ArrayList<T>, val pageCount: Int, val curPage: Int)