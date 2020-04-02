package com.xz.hh.data.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ListResponse(
    val courseId: Int?,
    val id: Int?,
    val name: String?,
    val order: Long?,
    val parentChapterId: Int?,
    val userControlSetTop: Boolean?,
    val visible: Int?
)