package com.hi.common.ktx.intent

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

inline fun <reified T : FragmentActivity> FragmentActivity.intentOf(extras: Bundle? = null): Intent {
    return Intent(this, T::class.java).apply {
        if (extras != null) {
            putExtras(extras)
        }
    }
}

inline fun <reified T : FragmentActivity> Fragment.intentOf(extras: Bundle? = null): Intent {
    return Intent(requireContext(), T::class.java).apply {
        if (extras != null) {
            putExtras(extras)
        }
    }
}

inline fun <reified T : FragmentActivity> FragmentActivity.startActivity(extras: Bundle? = null) {
    val intent = Intent(this, T::class.java).apply {
        if (extras != null) putExtras(extras)
    }
    startActivity(intent)
}

inline fun <reified T : FragmentActivity> Fragment.startActivity(extras: Bundle? = null) {
    val intent = Intent(requireContext(), T::class.java).apply {
        if (extras != null) putExtras(extras)
    }
    startActivity(intent)
}