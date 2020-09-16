package com.hi.main.ui

import com.alibaba.android.arouter.facade.annotation.Route
import com.hi.common.BaseActivity
import com.hi.common.constant.RouterPath
import com.hi.common.ktx.toolbar
import com.hi.main.R

/**
 * jetPack->paging
 */
@Route(path = RouterPath.PAGE_RECYCLER)
class PageRecyclerActivity : BaseActivity() {

    override fun layoutId() = R.layout.activity_page_recycler

    override fun init() {
        toolbar(R.string.page_layout)
    }
}