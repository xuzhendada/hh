package com.xz.hh.api

import com.xz.hh.data.response.ListResponse
import com.xz.hh.data.WanResponse
import com.xz.hh.data.response.ArticleResponse
import com.xz.hh.paging.PageDTO
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * wanAndroidApi
 */
interface Api {
    @GET("/wxarticle/chapters/json")
    suspend fun getList(): WanResponse<List<ListResponse>>

    @GET("article/list/{page}/json")
    suspend fun getHomeArticle(@Path("page") page: Int): WanResponse<PageDTO<ArticleResponse>>
}