package com.hi.common.ktx


import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.hi.common.R

/**
 * toolbar扩展
 */
fun AppCompatActivity.toolbar(title: CharSequence? = null, showHomeAsUp: Boolean = true) {
    val toolbar = findViewById<Toolbar>(R.id.toolbar)
    toolbar?.let {
        setSupportActionBar(toolbar)
        it.setNavigationOnClickListener {
            finish()
        }
    }
    supportActionBar?.let {
        it.setDisplayHomeAsUpEnabled(showHomeAsUp)
        it.setHomeButtonEnabled(true)
        it.title = title
    }
}

fun AppCompatActivity.toolbar(@StringRes title: Int, showHomeAsUp: Boolean = true) {
    toolbar(getString(title, showHomeAsUp))
}

/**
 * 更新Toolbar标题
 */
fun AppCompatActivity.toolbarTitle(title: CharSequence?) {
    supportActionBar?.title = title
}
