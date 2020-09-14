package com.hi.common.ktx

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.FragmentActivity
import com.alibaba.android.arouter.launcher.ARouter
import java.io.Serializable


/**
 * ARouter navigation
 */
fun navigate(path: String, vararg params: Pair<String, Any?>) {
    val bundle = Bundle()
    if (params.isNotEmpty())
        BundleArguments.fillBundleArguments(bundle, params)
    ARouter.getInstance().build(path).with(bundle).navigation()
}

/**
 * ARouter navigation
 */
fun FragmentActivity.navigate(path: String, vararg params: Pair<String, Any?>) {
    val bundle = Bundle()
    if (params.isNotEmpty())
        BundleArguments.fillBundleArguments(bundle, params)
    ARouter.getInstance().build(path).with(bundle).navigation(this)
}


object BundleArguments {

    fun fillBundleArguments(bundle: Bundle, params: Array<out Pair<String, Any?>>) {
        params.forEach {
            when (val value = it.second) {
                null -> bundle.putSerializable(it.first, null as Serializable?)
                is Int -> bundle.putInt(it.first, value)
                is Long -> bundle.putLong(it.first, value)
                is CharSequence -> bundle.putCharSequence(it.first, value)
                is String -> bundle.putString(it.first, value)
                is Float -> bundle.putFloat(it.first, value)
                is Double -> bundle.putDouble(it.first, value)
                is Char -> bundle.putChar(it.first, value)
                is Short -> bundle.putShort(it.first, value)
                is Boolean -> bundle.putBoolean(it.first, value)
                is Serializable -> bundle.putSerializable(it.first, value)
                is Parcelable -> bundle.putParcelable(it.first, value)
                is IntArray -> bundle.putIntArray(it.first, value)
                is LongArray -> bundle.putLongArray(it.first, value)
                is FloatArray -> bundle.putFloatArray(it.first, value)
                is DoubleArray -> bundle.putDoubleArray(it.first, value)
                is CharArray -> bundle.putCharArray(it.first, value)
                is ShortArray -> bundle.putShortArray(it.first, value)
                is BooleanArray -> bundle.putBooleanArray(it.first, value)
                else -> throw Exception("Intent extra ${it.first} has wrong type ${value.javaClass.name}")
            }
            return@forEach
        }
    }

}