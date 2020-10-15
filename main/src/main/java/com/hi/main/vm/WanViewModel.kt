package com.hi.main.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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
}