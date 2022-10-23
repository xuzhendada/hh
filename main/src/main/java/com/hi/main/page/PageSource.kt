package com.hi.main.page


import android.util.Log
import androidx.lifecycle.asLiveData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hi.common.api.ApiService
import com.hi.common.data.response.Article
import java.lang.Exception

class PageSource : PagingSource<Int, Article>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 0
            val result = ApiService.mApi.getHomeArticle(page)
            Log.d("TAG", "当前页$page")
            val dataList = result.asLiveData().value!!.data
            LoadResult.Page(
                data = dataList.datas,
                prevKey = null,
                nextKey = if (dataList.curPage == dataList.pageCount) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return 0
    }
}