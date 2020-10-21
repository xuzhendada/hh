package com.hi.common.data

sealed class HhResult<out R> {
    data class Success<out T>(val value: T) : HhResult<T>()
    data class Failure(val throwable: Throwable) : HhResult<Nothing>()
}