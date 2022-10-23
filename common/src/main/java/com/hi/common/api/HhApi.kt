package com.hi.common.api

import com.hi.common.data.PageListResult
import com.hi.common.data.response.ListResponse
import com.hi.common.data.WanResponse
import com.hi.common.data.response.Article
import com.hi.common.data.response.Banner
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * wanAndroidApi
 */
interface HhApi {

    @GET("/wxarticle/chapters/json")
    suspend fun getList(): WanResponse<List<ListResponse>>

    @GET("/article/list/{page}/json")
    suspend fun getHomeArticle(@Path("page") page: Int): WanResponse<PageListResult<Article>>

    @GET("/banner/json")
    suspend fun getBanner(): WanResponse<List<Banner>>
}