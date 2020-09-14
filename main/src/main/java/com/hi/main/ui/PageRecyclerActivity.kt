package com.hi.main.ui

import com.hi.common.BaseActivity
import com.hi.common.ktx.toolbar
import com.hi.main.R


class PageRecyclerActivity : BaseActivity() {

    override fun layoutId() = R.layout.activity_page_recycler

    override fun init() {
        toolbar(R.string.page_layout)
    }
}