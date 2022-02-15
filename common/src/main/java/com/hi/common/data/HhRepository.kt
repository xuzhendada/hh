package com.hi.common.data

import com.hi.common.api.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Singleton

/**
 *
 */
@Singleton
class HhRepository constructor(private val api: Api) {

    fun getArticle() = flow {
        emit(api.getList())
    }.flowOn(Dispatchers.IO)

    fun getHomeArticle(page: Int) = flow {
        emit(api.getHomeArticle(page))
    }.flowOn(Dispatchers.IO)

    fun getBanner() = flow {
        emit(api.getBanner())
    }
}