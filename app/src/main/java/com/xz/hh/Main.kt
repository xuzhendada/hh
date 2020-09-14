package com.xz.hh

import androidx.recyclerview.widget.GridLayoutManager
import com.hi.common.BaseActivity
import com.hi.common.adapter.ItemCell
import com.hi.common.adapter.StableAdapter
import com.hi.common.ktx.createStableAdapter
import com.hi.common.ktx.toolbar
import com.hi.main.permission.request
import com.hi.main.ui.PageRecyclerActivity
import com.hi.main.ui.SampleRecyclerActivity
import com.hi.main.ui.TopFoldActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by xuz on 2020/4/1 23:14
 */
class Main : BaseActivity() {
    private lateinit var mAdapter: StableAdapter

    override fun layoutId() = R.layout.activity_main

    override fun init() {
        toolbar("首页", false)
        mAdapter = createStableAdapter {
            onSimpleCallback { position ->
                val itemCell = mAdapter.currentList()[position] as BtnCell
                when (itemCell.itemId()) {
                    "${R.string.sample_recycler}" -> startActivity<SampleRecyclerActivity>()
                    "${R.string.request_permission}" ->
                        request(
                            android.Manifest.permission.CAMERA
                        ) {
                            onGranted {
                                toast("SUCCESS")
                            }
                            onDenied {
                                toast("DENIED")
                            }
                        }
                    "${R.string.coordinator_layout}" -> startActivity<TopFoldActivity>()
                    "${R.string.page_layout}" -> startActivity<PageRecyclerActivity>()
                }
            }
        }
        recycler.apply {
            adapter = mAdapter
            layoutManager = GridLayoutManager(this@Main, 2)
        }
    }

    override fun viewDrawn() {
        super.viewDrawn()
        val itemCellList = mutableListOf<ItemCell>()
        itemCellList.add(BtnCell(R.string.sample_recycler))
        itemCellList.add(BtnCell(R.string.request_permission))
        itemCellList.add(BtnCell(R.string.coordinator_layout))
        itemCellList.add(BtnCell(R.string.page_layout))
        mAdapter.submitList(itemCellList)
    }
}
