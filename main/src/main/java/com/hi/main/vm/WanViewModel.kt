package com.xz.hh.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.hi.common.api.ApiService
import com.hi.common.data.wanRequest
import com.hi.common.data.response.ListResponse
import com.hi.common.data.WanResponse

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

//    val pager by lazy {
//        Pager(
//            config = PagingConfig(20, 1),
//            pagingSourceFactory = { PageSource() }
//        ).flow.cachedIn(viewModelScope)
//    }
}