package com.xz.hh.paging

import androidx.paging.PagingSource
import com.hi.common.api.ApiService
import com.xz.hh.data.response.ArticleResponse
import java.lang.Exception

class PageSource : PagingSource<Int, ArticleResponse>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleResponse> {
        val page = params.key ?: 0
        return try {
            val response = ApiService.mApi.getHomeArticle(page).data!!
            LoadResult.Page(
                response.datas,
                if (page == 0) null else page - 1,
                if (response.over) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}