package com.hi.main.page


import androidx.paging.PagingSource
import com.hi.common.api.ApiService
import com.hi.common.data.response.Article
import java.lang.Exception

class  PageSource : PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 0
            val result = ApiService.mApi.getHomeArticle(page)
            LoadResult.Page(
                data = result.data.datas,
                prevKey = null,
                nextKey = if (result.data.curPage == result.data.pageCount) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}