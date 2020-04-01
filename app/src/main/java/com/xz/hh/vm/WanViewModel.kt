package com.xz.hh.vm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xz.hh.RetrofitUtil
import com.xz.hh.api.wanRequest
import com.xz.hh.request.ListRequest
import com.xz.hh.response.WanResponse

class WanViewModel : ViewModel() {
    private val listData: MutableLiveData<WanResponse<List<ListRequest>>> by lazy {
        MutableLiveData<WanResponse<List<ListRequest>>>().also {
            wanListRequest()
        }
    }

    fun subscribeList() = listData

    fun wanListRequest() {
        wanRequest<List<ListRequest>> {
            loader {
                RetrofitUtil.mApi.getList()
            }
            onRequestResult {
                listData.value = it
            }
        }
    }
}