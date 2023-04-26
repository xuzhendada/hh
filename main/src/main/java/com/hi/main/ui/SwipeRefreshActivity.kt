package com.hi.main.ui

import com.hi.common.BaseActivity
import com.hi.common.ktx.toolbar
import com.hi.main.R
import com.hi.main.databinding.ActivitySwipeRefreshBinding

class SwipeRefreshActivity : BaseActivity<ActivitySwipeRefreshBinding>() {
    override fun init() {
        toolbar(R.string.swipe_refresh)
        bind.swipeRefreshLayout.setOnRefreshListener {

        }
    }
}