package com.hi.main.permission

import java.util.concurrent.atomic.AtomicInteger

internal object PermissionsMap {
    private val atomicInteger = AtomicInteger(100)

    private val map = mutableMapOf<Int, PermissionsCallback>()

    fun put(callback: PermissionsCallback): Int {
        return atomicInteger.getAndIncrement().also {
            map[it] = callback
        }
    }

    fun get(requestCode: Int): PermissionsCallback? {
        return map[requestCode].also {
            map.remove(requestCode)
        }
    }
}