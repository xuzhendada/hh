package com.hi.common.ktx.intent

import android.content.Intent

class ActivityForResultDsl {
    private var ok: ((Intent?) -> Unit)? = null
    private var cancel: (() -> Unit)? = null

    infix fun onOk(block: (Intent?) -> Unit) {
        ok = block
    }

    infix fun onCancel(block: () -> Unit) {
        cancel = block
    }

    fun doOk(intent: Intent?) {
        ok?.invoke(intent)
    }

    fun doCancel() {
        cancel?.invoke()
    }
}