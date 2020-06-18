package com.xz.hh.ktx

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentActivity

/**
 * 防抖点击
 */
inline fun View.debounceClick(
    skipTime: Long = 500,
    crossinline click: (v: View) -> Unit = {}
) {
    var latestClick = 0L
    setOnClickListener {
        var currentClick = System.currentTimeMillis()
        if (currentClick - latestClick > skipTime) {
            latestClick = currentClick
            click.invoke(it)
        }
    }
}

@SuppressLint("ShowToast")
inline fun Context.toast(txt: String) {
    Toast.makeText(applicationContext, txt, Toast.LENGTH_LONG).show()
}