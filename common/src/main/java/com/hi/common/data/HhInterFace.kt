package com.hi.common.data

import com.hi.common.data.response.Article
import com.hi.common.data.response.Banner
import com.hi.common.data.response.ListResponse
import kotlinx.coroutines.flow.Flow

interface HhInterFace {
    suspend fun getList(): WanResponse<List<ListResponse>>
    suspend fun getHomeArticle(page: Int): WanResponse<PageListResult<Article>>
    suspend fun getBanner(): WanResponse<List<Banner>>
}