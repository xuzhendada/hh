package com.xz.hh.ktx


import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.xz.hh.R

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

/**
 * 更新Toolbar标题
 */
fun AppCompatActivity.toolbarTitle(title: CharSequence?) {
    supportActionBar?.title = title
}