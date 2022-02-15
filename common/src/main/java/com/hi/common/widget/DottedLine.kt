package com.hi.common.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import com.hi.common.R

/**
 * @author wbxuzhen
 * date : 2021/5/24 12:17
 * description :
 */
class DottedLine @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint = Paint()

    init {
        paint.color = getContext().getColor(R.color.colorPrimary)
        paint.strokeWidth = 5F
        paint.style = Paint.Style.STROKE
        paint.strokeCap = Paint.Cap.ROUND
        paint.isAntiAlias = true
        paint.isDither = true
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val startY = height.toFloat()
        val startX = width.toFloat()
        val dash = DashPathEffect(mutableListOf(8f, 10f, 8f, 10f).toFloatArray(), 0f)
        paint.pathEffect = dash
        val path = Path()
        path.moveTo(startX, 0f)
        path.lineTo(startX, startY)
        canvas?.drawPath(path, paint)
    }
}