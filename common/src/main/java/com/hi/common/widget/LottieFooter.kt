package com.hi.common.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.hi.common.R
import com.scwang.smartrefresh.layout.api.RefreshFooter
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.internal.InternalAbstract
import kotlinx.android.synthetic.main.lottie_footer.view.*
import kotlinx.android.synthetic.main.lottie_header.view.lottieView

/**
 * @author : wbxuzhen
 * @date : 2020/11/9 13:43
 * @description :
 */
class LottieFooter @JvmOverloads constructor(
    context: Context?, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : InternalAbstract(context, attrs, defStyleAttr), RefreshFooter {
    init {
        View.inflate(context, R.layout.lottie_footer, this)
    }

    override fun onStartAnimator(refreshLayout: RefreshLayout, height: Int, maxDragHeight: Int) {
        lottieView.playAnimation()
    }

    override fun onFinish(refreshLayout: RefreshLayout, success: Boolean): Int {
        lottieView.cancelAnimation()
        return super.onFinish(refreshLayout, success)
    }

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