package com.hi.common

import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import java.lang.Exception
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {

    protected lateinit var bind: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val type = javaClass.genericSuperclass
        if (type is ParameterizedType) {
            val clazz = type.actualTypeArguments[0] as Class<*>
            try {
                val method = clazz.getMethod("inflate", LayoutInflater::class.java)
                bind = method.invoke(null, layoutInflater) as T
                setContentView(bind.root)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        init()
    }

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