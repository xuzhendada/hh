package com.hi.common.ktx.intent

class PermissionsForResultDsl {
    private var granted: (() -> Unit)? = null
    private var denied: (() -> Unit)? = null

    infix fun onGranted(block: () -> Unit) {
        granted = block
    }

    infix fun onDenied(block: () -> Unit) {
        denied = block
    }

    fun doGranted() {
        granted?.invoke()
    }

    fun doDenied() {
        denied?.invoke()
    }
}
