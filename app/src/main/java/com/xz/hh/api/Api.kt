package com.xz.hh.api

import com.xz.hh.data.response.ListResponse
import com.xz.hh.data.WanResponse
import retrofit2.http.GET

interface Api {
    @GET("/wxarticle/chapters/json")
    suspend fun getList(): WanResponse<List<ListResponse>>
}