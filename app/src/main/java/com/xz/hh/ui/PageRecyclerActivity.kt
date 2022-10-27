package com.xz.hh.ui

import android.os.Bundle
import com.xz.hh.BaseActivity
import com.xz.hh.R
import com.xz.hh.ktx.toolbar


class PageRecyclerActivity : BaseActivity() {

    override fun layoutId() = R.layout.activity_page_recycler

    override fun init() {
        toolbar(R.string.page_layout)
    }
}