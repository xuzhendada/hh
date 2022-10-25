package com.hi.common.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.airbnb.lottie.LottieAnimationView
import com.hi.common.R
import com.scwang.smartrefresh.layout.api.RefreshFooter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.internal.InternalAbstract

/**
 * @author : wbxuzhen
 * @date : 2020/11/9 13:43
 * @description :
 */
class LottieFooter @JvmOverloads constructor(
    context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : InternalAbstract(context, attrs, defStyleAttr), RefreshFooter {
    private var lottieView: LottieAnimationView
    private var noMore: AppCompatTextView

    init {
        val view = View.inflate(context, R.layout.lottie_footer, this)
        lottieView = view.findViewById(R.id.lottieView)
        noMore = view.findViewById(R.id.noMore)
    }

    override fun onStartAnimator(refreshLayout: RefreshLayout, height: Int, maxDragHeight: Int) {
        lottieView.playAnimation()
    }

    override fun onFinish(refreshLayout: RefreshLayout, success: Boolean): Int {
        lottieView.cancelAnimation()
        return super.onFinish(refreshLayout, success)
    }

    @SuppressLint("RestrictedApi")
    override fun setNoMoreData(noMoreData: Boolean) = when (noMoreData) {
        true -> {
            lottieView.visibility = View.GONE
            noMore.visibility = View.VISIBLE
            true
        }
        false -> {
            lottieView.visibility = View.VISIBLE
            noMore.visibility = View.GONE
            true
        }
    }
}