package com.xz.hh.ktx

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity

/**
 * activity跳转扩展
 */
inline fun <reified T : FragmentActivity> FragmentActivity.startActivity(extras: Bundle? = null) {
    val intent = Intent(this, T::class.java)
    extras?.let {
        intent.putExtras(it)
    }
    startActivity(intent)
}