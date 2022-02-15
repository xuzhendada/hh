package com.hi.common.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

/**
 *  Created by wbxuzhen on 2020/11/19 16:57.
 *  des:正方形布局
 */
class SquareLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        super.onMeasure(
            MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY),
            MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY)
        )
    }
}