package com.xz.hh.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.xz.hh.api.ApiService
import com.xz.hh.data.wanRequest
import com.xz.hh.data.response.ListResponse
import com.xz.hh.data.WanResponse
import com.xz.hh.data.response.ArticleResponse
import com.xz.hh.paging.PageSource

class WanViewModel : ViewModel() {
    private val listData: MutableLiveData<WanResponse<List<ListResponse>>> by lazy {
        MutableLiveData<WanResponse<List<ListResponse>>>().also {
            wanListRequest()
        }
    }

    fun subscribeList() = listData

    fun wanListRequest() {
        wanRequest<List<ListResponse>> {
            loader {
                ApiService.mApi.getList()
            }
            onRequestResult {
                listData.value = it
            }
        }
    }

    val pager by lazy {
        Pager(
            config = PagingConfig(20, 1),
            pagingSourceFactory = { PageSource() }
        ).flow.cachedIn(viewModelScope)
    }
}