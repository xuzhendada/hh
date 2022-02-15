package com.hi.common.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class WanRequest<T> {
    private lateinit var loader: suspend () -> WanResponse<T>
    private var requestResult: ((WanResponse<T>) -> Unit)? = null

    infix fun loader(block: suspend () -> WanResponse<T>) {
        loader = block
    }

    infix fun onRequestResult(block: (WanResponse<T>) -> Unit) {
        requestResult = block
    }

    fun request(viewModelScope: CoroutineScope) {
        viewModelScope.launch {
            try {
                val response = withContext(IO) {
                    loader()
                }
                requestResult?.invoke(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

inline fun <T> ViewModel.wanRequest(crossinline request: WanRequest<T>.() -> Unit) {
    WanRequest<T>().apply(request).request(viewModelScope)
}