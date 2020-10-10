package com.hi.common.data.response


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Tag(
    val name: String,
    val url: String
)