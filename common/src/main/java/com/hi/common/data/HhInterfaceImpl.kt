package com.hi.common.data

import com.hi.common.api.HhApi
import com.hi.common.data.response.Article
import com.hi.common.data.response.Banner
import com.hi.common.data.response.ListResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 *
 */
class HhInterfaceImpl @Inject constructor(
    private val api: HhApi
) :HhInterFace {
     override suspend fun getList(): Flow<WanResponse<List<ListResponse>>> =
        api.getList()

     override suspend fun getHomeArticle(page: Int): Flow<WanResponse<PageListResult<Article>>> =
        api.getHomeArticle(page)

     override suspend fun getBanner(): Flow<WanResponse<List<Banner>>> =
        api.getBanner()

}