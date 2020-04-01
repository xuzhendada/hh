package com.xz.hh.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WanResponse<T>(
    val data: T? = null,
    val errorCode: Int,
    val errorMsg: String?
)