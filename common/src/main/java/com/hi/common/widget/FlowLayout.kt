package com.hi.common.widget

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import kotlin.math.max

/**
 * Creatd by wbxuzhen on 2021/3/26 17:09
 * description:流式布局
 */
class FlowLayout @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ViewGroup(context, attrs, defStyleAttr) {
    private val allChildView = mutableListOf<MutableList<View>>()
    private val lineHeightList = mutableListOf<Int>()
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        allChildView.clear()
        lineHeightList.clear()
        val width = width
        var lineWidth = 0
        var lineHeight = 0
        var lineViews = mutableListOf<View>()
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val lp = child.layoutParams
            (lp as MarginLayoutParams).apply {
                val childWidth = child.measuredWidth
                val childHeight = child.measuredHeight
                if (childWidth + lineWidth + leftMargin + rightMargin > width) {
                    lineHeightList.add(lineHeight)
                    allChildView.add(lineViews)
                    lineWidth = 0
                    lineHeight = childHeight + topMargin + bottomMargin
                    lineViews = mutableListOf()
                }
                lineWidth += childWidth + leftMargin + rightMargin
                lineHeight = max(lineHeight, childHeight + topMargin + bottomMargin)
                lineViews.add(child)
            }
        }
        lineHeightList.add(lineHeight)
        allChildView.add(lineViews)
        var left = 0
        var top = 0
        for (i in 0 until allChildView.size) {
            lineViews = allChildView[i]
            lineHeight = lineHeightList[i]
            for (j in 0 until lineViews.size) {
                val child = lineViews[j]
                if (child.visibility == View.GONE) {
                    continue
                }
                val lp = child.layoutParams as MarginLayoutParams
                val cLeft = left + lp.leftMargin
                val cTop = top + lp.topMargin
                val cRight = cLeft + child.measuredWidth
                val cBottom = cTop + child.measuredHeight
                child.layout(cLeft, cTop, cRight, cBottom)
                left += child.measuredWidth + lp.leftMargin + lp.rightMargin
            }
            left = 0
            top += lineHeight
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val sizeWidth = MeasureSpec.getSize(widthMeasureSpec)
        val modeWidth = MeasureSpec.getMode(widthMeasureSpec)
        val sizeHeight = MeasureSpec.getSize(heightMeasureSpec)
        val modeHeight = MeasureSpec.getMode(heightMeasureSpec)
        var width = 0
        var height = 0
        var lineWidth = 0
        var lineHeight = 0
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
            val lp = layoutParams as MarginLayoutParams
            val childWidth = child.measuredWidth + lp.leftMargin + lp.rightMargin
            val childHeight = child.measuredHeight + lp.topMargin + lp.bottomMargin
            if (lineWidth + childWidth > sizeWidth) {
                width = max(width, lineWidth)
                lineWidth = childWidth
                height += lineHeight
                lineHeight = childHeight
            } else {
                lineWidth += childWidth
                lineHeight = max(lineHeight, childHeight)
            }
            if (i == childCount - 1) {
                width = max(width, lineWidth)
                height += lineHeight
            }
        }
        setMeasuredDimension(
            if (modeWidth == MeasureSpec.EXACTLY) sizeWidth else width,
            if (modeHeight == MeasureSpec.EXACTLY) sizeHeight else height
        )
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
//        val  valueAn:ValueAnimator = ObjectAnimator.ofFloat(0f,1f)
//        valueAn.setDuration(500)
//        valueAn.addUpdateListener {
//            val f = it.animatedValue as Float
//            val new =(w - oldw) *f+oldw
//            background.setBounds(left = 0 , right = )
//            background.invalidateSelf()
//            invalidate()
//        }
//        valueAn.start()
    }
}