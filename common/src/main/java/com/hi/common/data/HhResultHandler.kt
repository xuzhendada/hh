package com.hi.common.data

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

class HhResultHandler<T>() {
    private var success: ((T) -> Unit)? = null
    private var failure: ((Throwable) -> Unit)? = null

    infix fun onSuccess(block: (T) -> Unit) {
        success = block
    }

    infix fun onFailure(block: (e: Throwable) -> Unit) {
        failure = block
    }

    fun invokeSuccess(content: T) {
        success?.invoke(content)
    }

    fun invokeFailure(throwable: Throwable) {
        failure?.invoke(throwable)
    }
}

inline fun <E> LiveData<HhResult<E>>.handleResult(
    owner: LifecycleOwner, crossinline handler: HhResultHandler<E>.() -> Unit
) {
    val responseHandler = HhResultHandler<E>().apply(handler)
    observe(owner) {
        when (it) {
            is HhResult.Success -> responseHandler.invokeSuccess(it.value)
            is HhResult.Failure -> responseHandler.invokeFailure(it.throwable)
        }
    }
}