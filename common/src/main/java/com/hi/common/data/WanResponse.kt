package com.hi.common.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class WanResponse<T>(
    val data: T,
    val errorCode: Int,
    val errorMsg: String?
)