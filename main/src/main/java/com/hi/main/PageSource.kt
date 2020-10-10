package com.hi.main

import android.util.Log
import androidx.paging.PagingSource
import com.hi.common.adapter.ItemCell
import com.hi.common.api.ApiService
import com.hi.main.cells.ArticleCell
import java.lang.Exception

class PageSource : PagingSource<Int, ItemCell>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemCell> {
        return try {
            val page = params.key ?: 0
            val result = ApiService.mApi.getHomeArticle(page)
            val cellList = mutableListOf<ItemCell>()
            result.data?.datas?.forEach {
                cellList.add(ArticleCell(it))
            }
            LoadResult.Page(
                data = cellList,
                prevKey = null,
                nextKey = if (result.data!!.curPage == result.data!!.pageCount) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}