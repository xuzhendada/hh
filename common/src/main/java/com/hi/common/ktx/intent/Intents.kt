package com.hi.common.ktx.intent

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

@Suppress("unused")
inline fun <reified T : FragmentActivity> FragmentActivity.intentOf(extras: Bundle? = null): Intent {
    return Intent(this, T::class.java).apply {
        if (extras != null) {
            putExtras(extras)
        }
    }
}

@Suppress("unused")
inline fun <reified T : FragmentActivity> Fragment.intentOf(extras: Bundle? = null): Intent {
    return Intent(requireContext(), T::class.java).apply {
        if (extras != null) {
            putExtras(extras)
        }
    }
}