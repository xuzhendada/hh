package com.hi.main.ui

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hi.common.BaseActivity
import com.hi.common.ktx.debounceClick
import com.hi.common.ktx.toast
import com.hi.common.ktx.toolbar
import com.hi.common.room.STUDENT_DATA
import com.hi.main.R
import com.hi.main.databinding.ActivityFlowLayoutTestBinding

class FlowLayoutTestActivity : BaseActivity<ActivityFlowLayoutTestBinding>() {
    override fun init() {
        toolbar(R.string.flow_layout)
        val lp = ViewGroup.MarginLayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        lp.apply {
            leftMargin = 16
            rightMargin = 16
            topMargin = 8
            marginEnd = 8
        }
        STUDENT_DATA.subList(0, 20).forEach {
            bind.flowLayout.addView(
                itemFlowView(it), lp
            )
        }
    }

    /**
     * 构建子itemView
     */
    private fun itemFlowView(
        title: String,
    ): TextView {
        val view = TextView(this)
        view.apply {
            text = title
            textSize = 12.0F
            setTextColor(this.resources.getColor(android.R.color.white, null))
            setBackgroundResource(R.drawable.bg_item_flow_layout)
            debounceClick {
                toast(title)
            }
        }
        return view
    }
}

