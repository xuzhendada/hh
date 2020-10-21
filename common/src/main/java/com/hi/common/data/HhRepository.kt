package com.hi.common.data

import com.hi.common.api.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 *
 */
class HhRepository constructor(private val api: Api) {

    fun getArticle() = flow {
        emit(api.getList())
    }.flowOn(Dispatchers.IO)

}