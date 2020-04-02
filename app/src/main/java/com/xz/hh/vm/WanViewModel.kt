package com.xz.hh.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xz.hh.api.ApiService
import com.xz.hh.data.wanRequest
import com.xz.hh.data.response.ListResponse
import com.xz.hh.data.WanResponse

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