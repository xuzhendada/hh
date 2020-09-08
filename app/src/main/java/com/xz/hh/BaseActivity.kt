package com.xz.hh

import android.os.Bundle
import android.os.Looper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
        init()
    }

    @LayoutRes
    abstract fun layoutId(): Int
    abstract fun init()
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