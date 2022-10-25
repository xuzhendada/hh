package com.hi.common.data

import com.hi.common.api.HhApi
import com.hi.common.data.response.Article
import com.hi.common.data.response.Banner
import com.hi.common.data.response.ListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 *
 */
class HhInterfaceImpl @Inject constructor(
    private val api: HhApi
) : HhInterFace {
    override suspend fun getList(): WanResponse<List<ListResponse>> = api.getList()

    override suspend fun getHomeArticle(page: Int): WanResponse<PageListResult<Article>> =
        api.getHomeArticle(page)

    override suspend fun getBanner(): WanResponse<List<Banner>> = api.getBanner()

}