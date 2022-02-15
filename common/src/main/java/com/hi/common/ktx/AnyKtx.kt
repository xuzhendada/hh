package com.hi.common.ktx

/**
 * @author : wbxuzhen
 * @date : 2020/11/13 13:33
 * @description :
 */
inline fun <reified T> Any.sameAs(block: (T) -> Unit = {}): Boolean {
    return if (this is T) {
        block.invoke(this)
        true
    } else {
        false
    }
}