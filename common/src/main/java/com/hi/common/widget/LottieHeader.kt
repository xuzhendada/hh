package com.hi.common.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.airbnb.lottie.LottieAnimationView
import com.hi.common.R
import com.scwang.smartrefresh.layout.api.RefreshHeader
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.internal.InternalAbstract

/**
 * @author : wbxuzhen
 * @date : 2020/11/9 10:27
 * @description :
 */
class LottieHeader @JvmOverloads constructor(
    context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : InternalAbstract(context, attrs, defStyleAttr), RefreshHeader {
    private var lottieView: LottieAnimationView
    init {
       val view= View.inflate(context, R.layout.lottie_header, this)
        lottieView= view.findViewById(R.id.lottieView)
    }

    override fun onMoving(
        isDragging: Boolean,
        percent: Float,
        offset: Int,
        height: Int,
        maxDragHeight: Int
    ) {
        super.onMoving(isDragging, percent, offset, height, maxDragHeight)
        lottieView.progress = percent
    }

    override fun onStartAnimator(refreshLayout: RefreshLayout, height: Int, maxDragHeight: Int) {
        lottieView.playAnimation()
    }

    override fun onFinish(refreshLayout: RefreshLayout, success: Boolean): Int {
        lottieView.cancelAnimation()
        return super.onFinish(refreshLayout, success)
    }
}