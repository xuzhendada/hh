package com.hi.common.ktx

import android.content.Context
import androidx.annotation.DimenRes

fun Context.dimen(@DimenRes resource: Int) = resources.getDimensionPixelOffset(resource)