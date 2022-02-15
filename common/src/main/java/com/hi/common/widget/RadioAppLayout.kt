package com.hi.common.widget

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.appbar.AppBarLayout

class RadioAppLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppBarLayout(context, attrs, defStyleAttr) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        super.onMeasure(
            widthMeasureSpec,
            MeasureSpec.makeMeasureSpec(width * 3 / 4, MeasureSpec.EXACTLY)
        )
    }
}