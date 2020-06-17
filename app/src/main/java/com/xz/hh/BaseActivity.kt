package com.xz.hh

import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    private var beforeViewDrawn = true
    override fun onResume() {
        super.onResume()
        if (beforeViewDrawn) {
            beforeViewDrawn = false
            Looper.myQueue().addIdleHandler {
                viewDrawn()
                false
            }
        }
    }

    open fun viewDrawn() {
        //
    }
}