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
    override suspend fun getList(): Flow<WanResponse<List<ListResponse>>> = flow {
        api.getList()
    }


    override suspend fun getHomeArticle(page: Int): Flow<WanResponse<PageListResult<Article>>> =
        flow {
            api.getHomeArticle(page)

        }

    override suspend fun getBanner(): Flow<WanResponse<List<Banner>>> = flow {
        api.getBanner()
    }

}