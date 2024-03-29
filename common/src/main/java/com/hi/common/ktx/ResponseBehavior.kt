package com.hi.common.ktx

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.hi.common.data.WanResponse

class ResponseBehavior<T> {
    private var success: ((T) -> Unit)? = null
    private var fail: ((errorCode: Int, message: String?) -> Unit)? = null

    infix fun onSuccess(block: (T) -> Unit) {
        success = block
    }

    infix fun onFail(block: (errorCode: Int, message: String?) -> Unit) {
        fail = block
    }

    fun behavior(response: WanResponse<T>) {
        if (response.data != null) {
            success?.invoke(response.data)
        } else {
            fail?.invoke(response.errorCode, response.errorMsg)
        }
    }
}

/**
 * 处理接口响应
 */
inline fun <T> LiveData<WanResponse<T>>.applyResponse(
    owner: LifecycleOwner, crossinline behavior: ResponseBehavior<T>.() -> Unit
) {
    this.observe(owner, Observer {
        ResponseBehavior<T>().apply(behavior).behavior(it)
    })
}