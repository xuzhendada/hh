package com.xz.hh.api

import com.xz.hh.request.ListRequest
import com.xz.hh.response.WanResponse
import retrofit2.http.GET

interface Api {
    @GET("/wxarticle/chapters/json")
    suspend fun getList(): WanResponse<List<ListRequest>>
}