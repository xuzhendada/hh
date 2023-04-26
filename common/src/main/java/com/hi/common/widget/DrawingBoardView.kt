package com.hi.common.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ColorRes
import kotlin.math.abs

/**
 * 画板
 */
class DrawingBoardView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private class DrawPath {
        var path: Path = Path()
        var paint: Paint = Paint()
    }

    private val TOUCH_TOLERANCE = 4

    private var paint: Paint = Paint()

    //画布
    private lateinit var canvas: Canvas

    //画笔宽度
    private var penSize = 10F

    //画笔颜色
    private var penColor = Color.BLACK

    //背景色
    private var backgroundColor = Color.TRANSPARENT

    //画布的画笔
    private var bitMapPaint: Paint = Paint(Paint.DITHER_FLAG)

    private var screenWidth: Int = 0
    private var screenHeight: Int = 0
    private var mX: Float = 0F
    private var mY: Float = 0F
    private var path: Path? = null

    private val savePath = mutableListOf<DrawPath>()

    private lateinit var bitmap: Bitmap

    private var drawPath: DrawPath? = null

    init {
        paint.apply {
            isAntiAlias = true
            style = Paint.Style.STROKE
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
            strokeWidth = penSize
            color = penColor
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        screenHeight = height
        screenWidth = width
        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        canvas = Canvas(bitmap)
        canvas.drawColor(backgroundColor)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            it.drawColor(backgroundColor)
            it.drawBitmap(bitmap, 0F, 0F, bitMapPaint)
            path?.let { path ->
                canvas.drawPath(path, paint)
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            val x = it.x
            val y = it.y
            when (it.action) {
                MotionEvent.ACTION_DOWN -> {
                    path = Path()
                    drawPath = DrawPath()
                    drawPath?.path = path ?: Path()
                    drawPath?.paint = paint
                    touchStart(x, y)
                    invalidate()
                }

                MotionEvent.ACTION_MOVE -> {
                    touchMove(x, y)
                    invalidate()
                }

                MotionEvent.ACTION_UP -> {
                    touchUp()
                    invalidate()
                }
            }
        }
        return true
    }

    fun getBitMap() = bitmap

    private fun touchStart(x: Float, y: Float) {
        path?.let {
            it.moveTo(x, y)
            mX = x
            mY = y
        }
    }

    private fun touchMove(x: Float, y: Float) {
        path?.let {
            val dx = abs(x - mX)
            val dy = abs(mY - y)
            if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                it.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2)
                mX = x
                mY = y
            }
        }
    }

    private fun touchUp() {
        path?.let {
            it.lineTo(mX, mY)
            canvas.drawPath(it, paint)
            savePath.add(drawPath ?: DrawPath())
            path = null
        }
    }

    fun setPenColor(@ColorRes color: Int) {
        penColor = color
    }

    fun setGroundColor(@ColorRes color: Int) {
        backgroundColor = color
    }

    fun setPenSize(size: Int) {
        paint.strokeWidth = if (size > 0) size.toFloat() else 10F
    }

    fun redo() {
        savePath.clear()
        canvas.drawColor(backgroundColor, PorterDuff.Mode.CLEAR)
        invalidate()
    }
}